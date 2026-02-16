package com.example.inventory.example.controller;

import com.example.commons.dto.inventory.*;
import com.example.commons.enums.LocationType;
import com.example.commons.enums.UnitOfMeasure;
import com.example.inventory.example.service.InventoryDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryDtoService inventoryService;

    public InventoryController(InventoryDtoService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/locations/add")
    public ResponseEntity<StorageLocationResponse> addStorageLocation(@RequestBody StorageLocationRequest request) {
        StorageLocationResponse location = inventoryService.addStorageLocation(request);
        return ResponseEntity.ok(location);
    }

    @PostMapping("/stages/add")
    public ResponseEntity<StageResponse> addStage(@RequestBody StageRequest request) {
        StageResponse stage = inventoryService.addStage(request);
        return ResponseEntity.ok(stage);
    }

    @PostMapping("/transitions/add")
    public ResponseEntity<StageTransitionResponse> addStageTransition(@RequestBody StageTransitionRequest request) {
        StageTransitionResponse transition = inventoryService.addStageTransition(request);
        return ResponseEntity.ok(transition);
    }

    @PostMapping("/products/add")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        ProductResponse product = inventoryService.addProduct(request);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/uoms/add")
    public ResponseEntity<ProductUomResponse> addUom(@RequestBody ProductUomRequest request) {
        ProductUomResponse productUom = inventoryService.addUom(request);
        return ResponseEntity.ok(productUom);
    }

    @PostMapping("/conversions/add")
    public ResponseEntity<UomConversionResponse> addConversion(@RequestBody UomConversionRequest request) {
        UomConversionResponse conversion = inventoryService.addConversion(request);
        return ResponseEntity.ok(conversion);
    }

    @PostMapping("/add")
    public ResponseEntity<InventoryResponse> addInventory(@RequestBody InventoryRequest request) {
        InventoryResponse inventory = inventoryService.addInventory(request);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/adjust")
    public ResponseEntity<InventoryResponse> adjustQuantity(@RequestBody AdjustInventoryRequest request) {
        InventoryResponse inventory = inventoryService.adjustQuantity(request);
        return ResponseEntity.ok(inventory);
    }

    @GetMapping("/{skuId}")
    public ResponseEntity<List<InventoryResponse>> getInventory(@PathVariable("skuId") String skuId,
            @RequestParam("unitOfMeasure") UnitOfMeasure unitOfMeasure) {
        List<InventoryResponse> inventories = inventoryService.getInventory(skuId, unitOfMeasure);
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/locations")
    public ResponseEntity<List<StorageLocationResponse>> getLocationsByType(@RequestParam("type") LocationType type) {
        List<StorageLocationResponse> locations = inventoryService.getLocationsByType(type);
        return ResponseEntity.ok(locations);
    }

    @PostMapping("/move")
    public ResponseEntity<InventoryResponse> moveInventory(@RequestBody MoveInventoryRequest request) {
        InventoryResponse toInventory = inventoryService.moveInventory(request);
        return ResponseEntity.ok(toInventory);
    }

    @PostMapping("/convert")
    public ResponseEntity<InventoryResponse> convertUom(@RequestBody ConvertInventoryUomRequest request) {
        InventoryResponse toInventory = inventoryService.convertUom(request);
        return ResponseEntity.ok(toInventory);
    }

    @PostMapping("/move/stage")
    public ResponseEntity<InventoryResponse> moveToStage(@RequestBody TransitionInventoryRequest request) {
        InventoryResponse toInventory = inventoryService.moveToStage(request);
        return ResponseEntity.ok(toInventory);
    }
}
