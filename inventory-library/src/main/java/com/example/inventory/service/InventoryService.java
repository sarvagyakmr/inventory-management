package com.example.inventory.service;

import com.example.inventory.model.Inventory;
import com.example.inventory.model.LocationType;
import com.example.inventory.model.StorageLocation;

import java.util.List;

public interface InventoryService {
    StorageLocation addStorageLocation(LocationType type, String description);
    Inventory addProduct(String productId, String locationId);
    Inventory adjustQuantity(String productId, String locationId, int quantityChange);
    List<Inventory> getInventory(String productId);
    List<StorageLocation> getLocationsByType(LocationType type);
    Inventory moveInventory(String productId, String fromLocationId, String toLocationId, int quantityToMove);
}
