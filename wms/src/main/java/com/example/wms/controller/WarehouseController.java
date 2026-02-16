package com.example.wms.controller;

import com.example.inventory.model.Product;
import com.example.wms.model.Aisle;
import com.example.wms.model.Box;
import com.example.wms.model.Item;
import com.example.wms.model.Location;
import com.example.wms.model.LocationCapacityType;
import com.example.wms.model.QcStatus;
import com.example.wms.model.StorageArea;
import com.example.wms.model.StorageAreaType;
import com.example.wms.service.WarehouseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * REST APIs for WMS storage location configuration.
 * Exposed to create/update StorageArea, Aisle, Location, and Box entities.
 * Follows pattern of example-usage and oms controllers.
 * Product validation calls OMS API (OMS is source of truth).
 */
@RestController
@RequestMapping("/api/wms")
public class WarehouseController {

    private final WarehouseService warehouseService;
    private final RestTemplate restTemplate = new RestTemplate();

    // OMS base URL from props (e.g., http://localhost:8081)
    @Value("${oms.base-url:http://localhost:8081}")
    private String omsBaseUrl;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    /**
     * Create StorageArea: POST /api/wms/storage-areas?description=...&type=...
     * Type required (INWARD, OUTWARD, STORAGE, PIGEON_HOLE). ID auto-incremented.
     */
    @PostMapping("/storage-areas")
    public ResponseEntity<StorageArea> createStorageArea(@RequestParam("description") String description,
            @RequestParam("type") StorageAreaType type) {
        StorageArea area = warehouseService.createStorageArea(description, type);
        return ResponseEntity.ok(area);
    }

    /**
     * Create Aisle in storage area: POST /api/wms/aisles?storageAreaId=...
     * ID generated as S{storageAreaId}-A{N}
     */
    @PostMapping("/aisles")
    public ResponseEntity<Aisle> createAisle(@RequestParam("storageAreaId") Long storageAreaId) {
        Aisle aisle = warehouseService.createAisle(storageAreaId);
        return ResponseEntity.ok(aisle);
    }

    /**
     * Create Location in aisle: POST
     * /api/wms/locations?storageAreaId=...&aisleId=...&type=...
     * Type required (SINGLE, MULTI). ID generated as {aisleId}-L{M} e.g. S1-A3-L4
     */
    @PostMapping("/locations")
    public ResponseEntity<Location> createLocation(@RequestParam("storageAreaId") Long storageAreaId,
            @RequestParam("aisleId") String aisleId,
            @RequestParam("type") LocationCapacityType type) {
        Location location = warehouseService.createLocation(storageAreaId, aisleId, type);
        return ResponseEntity.ok(location);
    }

    /**
     * Create Box: POST /api/wms/boxes?type=...&qcStatus=...&orderId=...
     * ID auto-generated Long; type=StorageAreaType, qcStatus required.
     * If type=INWARD, orderId required and validated via OMS API (OMS source of
     * truth for InwardOrder).
     * Location nullable.
     */
    @PostMapping("/boxes")
    public ResponseEntity<Box> createBox(@RequestParam("type") StorageAreaType type,
            @RequestParam("qcStatus") QcStatus qcStatus,
            @RequestParam(value = "orderId", required = false) Long orderId) {
        // If INWARD, validate InwardOrder exists in OMS
        if (type == StorageAreaType.INWARD) {
            if (orderId == null || orderId <= 0) {
                throw new IllegalArgumentException("orderId required for INWARD box type");
            }
            try {
                String omsUrl = omsBaseUrl + "/api/oms/orders/inward/" + orderId;
                restTemplate.getForObject(omsUrl, Object.class); // validates existence (InwardOrder or 404)
            } catch (Exception e) {
                // Handle 404/not found from OMS
                throw new IllegalArgumentException("InwardOrder not found in OMS: " + orderId);
            }
        }
        Box box = warehouseService.createBox(type, qcStatus, orderId);
        return ResponseEntity.ok(box);
    }

    /**
     * Update Box location: POST /api/wms/boxes/{boxId}/location?locationId=...
     * Enforces Box.type must match StorageArea.type of location (nullable to
     * unassign).
     * If inward box assigned to inward location: calls OMS GRN API for linked
     * orderId.
     * Aggregates products/qty from Box.items (qty=1 per Item, uom="EACH") and posts
     * to
     * /api/oms/orders/inward/grn?orderId=... (OMS source of truth for inward txns).
     * 
     * @param boxId      path var
     * @param locationId param (optional; omit or empty to clear)
     */
    @PostMapping("/boxes/{boxId}/location")
    public ResponseEntity<Box> updateBoxLocation(@PathVariable("boxId") Long boxId,
            @RequestParam(value = "locationId", required = false) String locationId) {
        Box box = warehouseService.updateBoxLocation(boxId, locationId);

        // Trigger OMS GRN if inward box assigned to inward location (via StorageArea
        // type)
        if (box.getType() == StorageAreaType.INWARD && box.getOrderId() != null && box.getLocation() != null) {
            // Aggregate: productId -> qty (1/Item default)
            Map<String, Integer> productQuantities = box.getItems().stream()
                    .collect(Collectors.groupingBy(Item::getProductId, Collectors.summingInt(e -> 1)));

            // Build GRN items payload (matches OMS GrnItem: skuId, unitOfMeasure, quantity)
            List<Map<String, Object>> grnItems = productQuantities.entrySet().stream()
                    .map(entry -> {
                        Map<String, Object> grnItem = new HashMap<>();
                        grnItem.put("skuId", entry.getKey());
                        grnItem.put("unitOfMeasure", "EACH");
                        grnItem.put("quantity", entry.getValue());
                        return grnItem;
                    })
                    .collect(Collectors.toList());

            // Call OMS GRN API for the Box's linked inward order
            String grnUrl = omsBaseUrl + "/api/oms/orders/inward/grn?orderId=" + box.getOrderId();
            restTemplate.postForObject(grnUrl, grnItems, Object.class); // triggers inward/GRN (ignores resp)
        }

        return ResponseEntity.ok(box);
    }

    /**
     * Create Item: POST /api/wms/items?productId=...&qcStatus=...
     * ID auto-generated Long; ties to Product; qcStatus for QC validation.
     * Validates product existence via OMS API (OMS is source of truth, not direct
     * library).
     */
    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestParam("productId") String productId,
            @RequestParam("qcStatus") QcStatus qcStatus) {
        // Call OMS API to validate product (source of truth)
        try {
            String omsUrl = omsBaseUrl + "/api/oms/products/" + productId;
            Product product = restTemplate.getForObject(omsUrl, Product.class);
            if (product == null) {
                throw new IllegalArgumentException("Product not found in OMS: " + productId);
            }
        } catch (Exception e) {
            // Handle 404/not found from OMS or connection issues
            throw new IllegalArgumentException("Product not found in OMS (source of truth): " + productId);
        }

        Item item = warehouseService.createItem(productId, qcStatus);
        return ResponseEntity.ok(item);
    }

    /**
     * Add Item to Box: POST /api/wms/boxes/{boxId}/items?itemId=...
     * Updates via box_item mapping table. Enforces QC status match:
     * Item.qcStatus == Box.qcStatus.
     */
    @PostMapping("/boxes/{boxId}/items")
    public ResponseEntity<Box> addItemToBox(@PathVariable("boxId") Long boxId,
            @RequestParam("itemId") Long itemId) {
        Box box = warehouseService.addItemToBox(itemId, boxId);
        return ResponseEntity.ok(box);
    }
}