package com.example.oms.controller;

import com.example.commons.dto.inventory.*;
import com.example.commons.dto.oms.*;
import com.example.commons.enums.OrderStatus;
import com.example.oms.service.OmsDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/oms")
public class OmsController {

    private final OmsDtoService omsService;

    public OmsController(OmsDtoService omsService) {
        this.omsService = omsService;
    }

    @PostMapping("/locations/create")
    public ResponseEntity<StorageLocationResponse> createLocation(@RequestBody StorageLocationRequest request) {
        StorageLocationResponse location = omsService.createLocation(request);
        return ResponseEntity.ok(location);
    }

    @PostMapping("/products/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        ProductResponse product = omsService.createProduct(request);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/inventory/update")
    public ResponseEntity<InventoryResponse> updateInventory(@RequestBody AdjustInventoryRequest request) {
        InventoryResponse inventory = omsService.updateInventory(request);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/inventory/transition")
    public ResponseEntity<InventoryResponse> makeInventoryTransition(@RequestBody TransitionInventoryRequest request) {
        InventoryResponse inventory = omsService.makeInventoryTransition(request);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/inventory/convert")
    public ResponseEntity<InventoryResponse> convertProductUom(@RequestBody ConvertInventoryUomRequest request) {
        InventoryResponse inventory = omsService.convertProductUom(request);
        return ResponseEntity.ok(inventory);
    }

    // Supplier/Customer create APIs moved to master-data module (/api/master-data).
    // See MasterDataController for /suppliers/add and /customers/add.

    @PostMapping("/orders/inward/create")
    public ResponseEntity<InwardOrderResponse> createInwardOrder(@RequestBody InwardOrderRequest request) {
        InwardOrderResponse order = omsService.createInwardOrder(request);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/orders/update/status")
    public ResponseEntity<InwardOrderResponse> updateOrderStatus(@RequestParam("orderId") Long orderId,
            @RequestParam("status") OrderStatus status) {
        InwardOrderResponse order = omsService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/products/uom/add")
    public ResponseEntity<ProductUomResponse> addProductUom(@RequestBody ProductUomRequest request) {
        ProductUomResponse productUom = omsService.addProductUom(request);
        return ResponseEntity.ok(productUom);
    }

    @PostMapping("/orders/inward/grn")
    public ResponseEntity<GrnResponse> createGrn(@RequestBody GrnRequest request) {
        GrnResponse grn = omsService.createGrn(request.getOrderId(), request.getItems());
        return ResponseEntity.ok(grn);
    }

    @PostMapping("/orders/purchase/create")
    public ResponseEntity<PurchaseOrderResponse> createPurchaseOrder(@RequestBody PurchaseOrderRequest request) {
        PurchaseOrderResponse order = omsService.createPurchaseOrder(request);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/orders/purchase/{orderId}")
    public ResponseEntity<PurchaseOrderResponse> getPurchaseOrderById(@PathVariable("orderId") Long orderId) {
        PurchaseOrderResponse order = omsService.getPurchaseOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/products/{skuId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("skuId") String skuId) {
        ProductResponse product = omsService.getProduct(skuId);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/orders/inward/{orderId}")
    public ResponseEntity<InwardOrderResponse> getInwardOrder(@PathVariable("orderId") Long orderId) {
        InwardOrderResponse order = omsService.getInwardOrder(orderId);
        return ResponseEntity.ok(order);
    }
}
