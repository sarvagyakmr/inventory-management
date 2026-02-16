package com.example.oms.controller;

import com.example.inventory.model.Inventory;
import com.example.inventory.model.LocationType;
import com.example.inventory.model.Product;
import com.example.inventory.model.ProductUom;
import com.example.inventory.model.StorageLocation;
import com.example.oms.model.Customer;
import com.example.oms.model.Grn;
import com.example.oms.model.GrnItem;
import com.example.oms.model.InwardOrder;
import com.example.oms.model.InwardOrderItem;
import com.example.oms.model.OrderStatus;
import com.example.oms.model.PurchaseOrder;
import com.example.oms.model.PurchaseOrderItem;
import com.example.oms.model.Supplier;
import com.example.oms.service.OmsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oms")
public class OmsController {

    private final OmsService omsService;

    public OmsController(OmsService omsService) {
        this.omsService = omsService;
    }

    @PostMapping("/locations/create")
    public ResponseEntity<StorageLocation> createLocation(@RequestParam("type") LocationType type,
            @RequestParam("description") String description) {
        StorageLocation location = omsService.createLocation(type, description);
        return ResponseEntity.ok(location);
    }

    @PostMapping("/products/create")
    public ResponseEntity<Product> createProduct(@RequestParam("skuId") String skuId) {
        Product product = omsService.createProduct(skuId);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/inventory/update")
    public ResponseEntity<Inventory> updateInventory(@RequestParam("skuId") String skuId,
            @RequestParam("unitOfMeasure") String unitOfMeasure, @RequestParam("locationId") String locationId,
            @RequestParam("stageId") Long stageId, @RequestParam("quantityChange") int quantityChange,
            @RequestParam("messageId") String messageId) {
        Inventory inventory = omsService.updateInventory(skuId, unitOfMeasure, locationId, stageId, quantityChange,
                messageId);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/inventory/transition")
    public ResponseEntity<Inventory> makeInventoryTransition(@RequestParam("skuId") String skuId,
            @RequestParam("unitOfMeasure") String unitOfMeasure, @RequestParam("locationId") String locationId,
            @RequestParam("fromStageId") Long fromStageId, @RequestParam("toStageId") Long toStageId,
            @RequestParam("quantityToMove") int quantityToMove, @RequestParam("messageId") String messageId) {
        Inventory inventory = omsService.makeInventoryTransition(skuId, unitOfMeasure, locationId, fromStageId,
                toStageId, quantityToMove, messageId);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/inventory/convert")
    public ResponseEntity<Inventory> convertProductUom(@RequestParam("skuId") String skuId,
            @RequestParam("fromUnitOfMeasure") String fromUnitOfMeasure,
            @RequestParam("toUnitOfMeasure") String toUnitOfMeasure, @RequestParam("stageId") Long stageId,
            @RequestParam("quantityToConvert") int quantityToConvert, @RequestParam("locationId") String locationId,
            @RequestParam("messageId") String messageId) {
        Inventory inventory = omsService.convertProductUom(skuId, fromUnitOfMeasure, toUnitOfMeasure, stageId,
                quantityToConvert, locationId, messageId);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/suppliers/add")
    public ResponseEntity<Supplier> addSupplier(@RequestParam("name") String name,
            @RequestParam("contactInfo") String contactInfo) {
        Supplier supplier = omsService.addSupplier(name, contactInfo);
        return ResponseEntity.ok(supplier);
    }

    @PostMapping("/orders/inward/create")
    public ResponseEntity<InwardOrder> createInwardOrder(@RequestParam("supplierId") Long supplierId,
            @RequestParam("locationId") String locationId, @RequestBody List<InwardOrderItem> items) {
        // order now created for a location (GRN uses order's location)
        InwardOrder order = omsService.createInwardOrder(supplierId, locationId, items);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/orders/update/status")
    public ResponseEntity<InwardOrder> updateOrderStatus(@RequestParam("orderId") Long orderId,
            @RequestParam("status") OrderStatus status) {
        InwardOrder order = omsService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/products/uom/add")
    public ResponseEntity<ProductUom> addProductUom(@RequestParam("skuId") String skuId,
            @RequestParam("unitOfMeasure") String unitOfMeasure) {
        ProductUom productUom = omsService.addProductUom(skuId, unitOfMeasure);
        return ResponseEntity.ok(productUom);
    }

    @PostMapping("/orders/inward/grn")
    public ResponseEntity<Grn> createGrn(@RequestParam("orderId") Long orderId,
            @RequestBody List<GrnItem> items) {
        // allows multiple GRNs while PROCESSING; inwards products to RECEIVED stage in
        // IMS
        // (location now from order, not GRN step)
        Grn grn = omsService.createGrn(orderId, items);
        return ResponseEntity.ok(grn);
    }

    @PostMapping("/customers/add")
    public ResponseEntity<Customer> addCustomer(@RequestParam("name") String name,
            @RequestParam("contactInfo") String contactInfo) {
        Customer customer = omsService.addCustomer(name, contactInfo);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/orders/purchase/create")
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestParam("customerId") Long customerId,
            @RequestParam("locationId") String locationId, @RequestBody List<PurchaseOrderItem> items) {
        // purchase order for customer+location; fulfillable status set based on
        // inventory check in RECEIVED/AVAILABLE
        PurchaseOrder order = omsService.createPurchaseOrder(customerId, locationId, items);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/orders/purchase/{orderId}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrderById(@PathVariable("orderId") Long orderId) {
        PurchaseOrder order = omsService.getPurchaseOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    /**
     * Get Product by ID (skuId). OMS is the source of truth for products.
     * WMS calls this API for validation (e.g., when creating Items).
     */
    @GetMapping("/products/{skuId}")
    public ResponseEntity<Product> getProduct(@PathVariable("skuId") String skuId) {
        Product product = omsService.getProduct(skuId);
        return ResponseEntity.ok(product);
    }

    /**
     * Get InwardOrder by ID. OMS is source of truth for orders.
     * Used by WMS for Box creation when type=INWARD (requires orderId).
     */
    @GetMapping("/orders/inward/{orderId}")
    public ResponseEntity<InwardOrder> getInwardOrder(@PathVariable("orderId") Long orderId) {
        InwardOrder order = omsService.getInwardOrder(orderId);
        return ResponseEntity.ok(order);
    }
}
