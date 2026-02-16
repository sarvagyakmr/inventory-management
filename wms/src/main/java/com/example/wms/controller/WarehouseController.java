package com.example.wms.controller;

import com.example.commons.dto.wms.*;
import com.example.commons.enums.StorageAreaType;
import com.example.wms.service.WarehouseDtoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/wms")
public class WarehouseController {

    private final WarehouseDtoService warehouseService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${oms.base-url:http://localhost:8081}")
    private String omsBaseUrl;

    public WarehouseController(WarehouseDtoService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping("/storage-areas")
    public ResponseEntity<StorageAreaResponse> createStorageArea(@RequestBody StorageAreaRequest request) {
        StorageAreaResponse area = warehouseService.createStorageArea(request);
        return ResponseEntity.ok(area);
    }

    @PostMapping("/aisles")
    public ResponseEntity<AisleResponse> createAisle(@RequestBody AisleRequest request) {
        AisleResponse aisle = warehouseService.createAisle(request);
        return ResponseEntity.ok(aisle);
    }

    @PostMapping("/locations")
    public ResponseEntity<LocationResponse> createLocation(@RequestBody LocationRequest request) {
        LocationResponse location = warehouseService.createLocation(request);
        return ResponseEntity.ok(location);
    }

    @PostMapping("/boxes")
    public ResponseEntity<BoxResponse> createBox(@RequestBody BoxRequest request) {
        if (request.getType() == StorageAreaType.INWARD) {
            if (request.getOrderId() == null || request.getOrderId() <= 0) {
                throw new IllegalArgumentException("orderId required for INWARD box type");
            }
            try {
                String omsUrl = omsBaseUrl + "/api/oms/orders/inward/" + request.getOrderId();
                restTemplate.getForObject(omsUrl, Object.class);
            } catch (Exception e) {
                throw new IllegalArgumentException("InwardOrder not found in OMS: " + request.getOrderId());
            }
        }
        BoxResponse box = warehouseService.createBox(request);
        return ResponseEntity.ok(box);
    }

    @PostMapping("/boxes/{boxId}/location")
    public ResponseEntity<BoxResponse> updateBoxLocation(@PathVariable("boxId") Long boxId,
            @RequestBody BoxLocationRequest request) {
        BoxResponse box = warehouseService.updateBoxLocation(boxId, request);

        if (box.getType() == StorageAreaType.INWARD && box.getOrderId() != null && box.getLocationId() != null) {
            Map<String, Integer> productQuantities = box.getItems().stream()
                    .collect(Collectors.groupingBy(ItemResponse::getProductId, Collectors.summingInt(e -> 1)));

            List<Map<String, Object>> grnItems = productQuantities.entrySet().stream()
                    .map(entry -> {
                        Map<String, Object> grnItem = new HashMap<>();
                        grnItem.put("skuId", entry.getKey());
                        grnItem.put("unitOfMeasure", "EACH");
                        grnItem.put("quantity", entry.getValue());
                        return grnItem;
                    })
                    .collect(Collectors.toList());

            String grnUrl = omsBaseUrl + "/api/oms/orders/inward/grn?orderId=" + box.getOrderId();
            restTemplate.postForObject(grnUrl, grnItems, Object.class);
        }

        return ResponseEntity.ok(box);
    }

    @PostMapping("/items")
    public ResponseEntity<ItemResponse> createItem(@RequestBody ItemRequest request) {
        try {
            String omsUrl = omsBaseUrl + "/api/oms/products/" + request.getProductId();
            Object product = restTemplate.getForObject(omsUrl, Object.class); // Just check existence
            if (product == null) {
                throw new IllegalArgumentException("Product not found in OMS: " + request.getProductId());
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Product not found in OMS (source of truth): " + request.getProductId());
        }

        ItemResponse item = warehouseService.createItem(request);
        return ResponseEntity.ok(item);
    }

    @PostMapping("/boxes/{boxId}/items")
    public ResponseEntity<BoxResponse> addItemToBox(@PathVariable("boxId") Long boxId,
            @RequestBody AddItemToBoxRequest request) {
        BoxResponse box = warehouseService.addItemToBox(boxId, request);
        return ResponseEntity.ok(box);
    }
}