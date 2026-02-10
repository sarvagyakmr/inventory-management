package com.example.inventory.example.controller;

import com.example.inventory.model.Inventory;
import com.example.inventory.model.LocationType;
import com.example.inventory.model.Product;
import com.example.inventory.model.ProductUom;
import com.example.inventory.model.Stage;
import com.example.inventory.model.StageTransition;
import com.example.inventory.model.StorageLocation;
import com.example.inventory.model.UomConversion;
import com.example.inventory.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/locations/add")
    public ResponseEntity<StorageLocation> addStorageLocation(@RequestParam("type") LocationType type,
            @RequestParam("description") String description) {
        StorageLocation location = inventoryService.addStorageLocation(type, description);
        return ResponseEntity.ok(location);
    }

    @PostMapping("/stages/add")
    public ResponseEntity<Stage> addStage(@RequestParam("name") String name, @RequestParam("description") String description) {
        Stage stage = inventoryService.addStage(name, description);
        return ResponseEntity.ok(stage);
    }

    @PostMapping("/transitions/add")
    public ResponseEntity<StageTransition> addStageTransition(@RequestParam("fromStageId") String fromStageId, @RequestParam("toStageId") String toStageId) {
        StageTransition transition = inventoryService.addStageTransition(fromStageId, toStageId);
        return ResponseEntity.ok(transition);
    }

    @PostMapping("/products/add")
    public ResponseEntity<Product> addProduct(@RequestParam("skuId") String skuId) {
        Product product = inventoryService.addProduct(skuId);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/uoms/add")
    public ResponseEntity<ProductUom> addUom(@RequestParam("skuId") String skuId,
            @RequestParam("unitOfMeasure") String unitOfMeasure) {
        ProductUom productUom = inventoryService.addUom(skuId, unitOfMeasure);
        return ResponseEntity.ok(productUom);
    }

    @PostMapping("/conversions/add")
    public ResponseEntity<UomConversion> addConversion(@RequestParam("skuId") String skuId,
            @RequestParam("fromUnitOfMeasure") String fromUnitOfMeasure,
            @RequestParam("toUnitOfMeasure") String toUnitOfMeasure,
            @RequestParam("factor") double factor) {
        UomConversion conversion = inventoryService.addConversion(skuId, fromUnitOfMeasure, toUnitOfMeasure, factor);
        return ResponseEntity.ok(conversion);
    }

    @PostMapping("/add")
    public ResponseEntity<Inventory> addInventory(@RequestParam("skuId") String skuId,
            @RequestParam("unitOfMeasure") String unitOfMeasure, @RequestParam("locationId") String locationId,
            @RequestParam("stageId") String stageId) {
        Inventory inventory = inventoryService.addInventory(skuId, unitOfMeasure, locationId, stageId);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/adjust")
    public ResponseEntity<Inventory> adjustQuantity(@RequestParam("skuId") String skuId,
            @RequestParam("unitOfMeasure") String unitOfMeasure, @RequestParam("locationId") String locationId,
            @RequestParam("stageId") String stageId,
            @RequestParam("quantityChange") int quantityChange, @RequestParam("messageId") String messageId) {
        Inventory inventory = inventoryService.adjustQuantity(skuId, unitOfMeasure, locationId, stageId, quantityChange, messageId);
        return ResponseEntity.ok(inventory);
    }

    @GetMapping("/{skuId}")
    public ResponseEntity<List<Inventory>> getInventory(@PathVariable("skuId") String skuId,
            @RequestParam("unitOfMeasure") String unitOfMeasure) {
        List<Inventory> inventories = inventoryService.getInventory(skuId, unitOfMeasure);
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/locations")
    public ResponseEntity<List<StorageLocation>> getLocationsByType(@RequestParam("type") LocationType type) {
        List<StorageLocation> locations = inventoryService.getLocationsByType(type);
        return ResponseEntity.ok(locations);
    }

    @PostMapping("/move")
    public ResponseEntity<Inventory> moveInventory(@RequestParam("skuId") String skuId,
            @RequestParam("unitOfMeasure") String unitOfMeasure,
            @RequestParam("fromLocationId") String fromLocationId, @RequestParam("toLocationId") String toLocationId,
            @RequestParam("stageId") String stageId,
            @RequestParam("quantityToMove") int quantityToMove) {
        Inventory toInventory = inventoryService.moveInventory(skuId, unitOfMeasure, fromLocationId, toLocationId, stageId, quantityToMove);
        return ResponseEntity.ok(toInventory);
    }

    @PostMapping("/convert")
    public ResponseEntity<Inventory> convertUom(@RequestParam("skuId") String skuId,
            @RequestParam("fromUnitOfMeasure") String fromUnitOfMeasure,
            @RequestParam("toUnitOfMeasure") String toUnitOfMeasure,
            @RequestParam("stageId") String stageId,
            @RequestParam("quantityToConvert") int quantityToConvert,
            @RequestParam("locationId") String locationId, @RequestParam("messageId") String messageId) {
        Inventory toInventory = inventoryService.convertUom(skuId, fromUnitOfMeasure, toUnitOfMeasure, stageId, quantityToConvert, locationId, messageId);
        return ResponseEntity.ok(toInventory);
    }

    @PostMapping("/move/stage")
    public ResponseEntity<Inventory> moveToStage(@RequestParam("skuId") String skuId,
            @RequestParam("unitOfMeasure") String unitOfMeasure, @RequestParam("locationId") String locationId,
            @RequestParam("fromStageId") String fromStageId, @RequestParam("toStageId") String toStageId,
            @RequestParam("quantityToMove") int quantityToMove, @RequestParam("messageId") String messageId) {
        Inventory toInventory = inventoryService.moveToStage(skuId, unitOfMeasure, locationId, fromStageId, toStageId, quantityToMove, messageId);
        return ResponseEntity.ok(toInventory);
    }
}
