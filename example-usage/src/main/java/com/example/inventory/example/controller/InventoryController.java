package com.example.inventory.example.controller;

import com.example.inventory.model.Inventory;
import com.example.inventory.model.LocationType;
import com.example.inventory.model.StorageLocation;
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

    @PostMapping("/add")
    public ResponseEntity<Inventory> addProduct(@RequestParam("productId") String productId, @RequestParam("locationId") String locationId) {
        Inventory inventory = inventoryService.addProduct(productId, locationId);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/adjust")
    public ResponseEntity<Inventory> adjustQuantity(@RequestParam("productId") String productId,
            @RequestParam("locationId") String locationId, @RequestParam("quantityChange") int quantityChange) {
        Inventory inventory = inventoryService.adjustQuantity(productId, locationId, quantityChange);
        return ResponseEntity.ok(inventory);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<Inventory>> getInventory(@PathVariable("productId") String productId) {
        List<Inventory> inventories = inventoryService.getInventory(productId);
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/locations")
    public ResponseEntity<List<StorageLocation>> getLocationsByType(@RequestParam("type") LocationType type) {
        List<StorageLocation> locations = inventoryService.getLocationsByType(type);
        return ResponseEntity.ok(locations);
    }

    @PostMapping("/move")
    public ResponseEntity<Inventory> moveInventory(@RequestParam("productId") String productId,
            @RequestParam("fromLocationId") String fromLocationId, @RequestParam("toLocationId") String toLocationId,
            @RequestParam("quantityToMove") int quantityToMove) {
        Inventory toInventory = inventoryService.moveInventory(productId, fromLocationId, toLocationId, quantityToMove);
        return ResponseEntity.ok(toInventory);
    }
}
