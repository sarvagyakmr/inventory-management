package com.example.inventory.service;

import com.example.inventory.model.Inventory;
import com.example.inventory.model.InventoryId;
import com.example.inventory.model.LocationType;
import com.example.inventory.model.StorageLocation;
import com.example.inventory.repository.InventoryRepository;
import com.example.inventory.repository.StorageLocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final StorageLocationRepository locationRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, StorageLocationRepository locationRepository) {
        this.inventoryRepository = inventoryRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    @Transactional
    public StorageLocation addStorageLocation(LocationType type, String description) {
        String locationId = UUID.randomUUID().toString();
        if (locationRepository.existsById(locationId)) {
            throw new IllegalArgumentException("Location already exists");  // rare for UUID
        }
        StorageLocation location = new StorageLocation(locationId, type, description);
        return locationRepository.save(location);
    }

    @Override
    @Transactional
    public Inventory addProduct(String productId, String locationId) {
        if (!locationRepository.existsById(locationId)) {
            throw new IllegalArgumentException("Location not found");
        }
        InventoryId invId = new InventoryId(productId, locationId);
        if (inventoryRepository.existsById(invId)) {
            throw new IllegalArgumentException("Product already exists at location");
        }
        Inventory inventory = new Inventory(productId, locationId, 0);
        return inventoryRepository.save(inventory);
    }

    @Override
    @Transactional
    public Inventory adjustQuantity(String productId, String locationId, int quantityChange) {
        InventoryId invId = new InventoryId(productId, locationId);
        Inventory inventory = inventoryRepository.findById(invId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found at location"));
        int newQuantity = inventory.getQuantity() + quantityChange;
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        inventory.setQuantity(newQuantity);
        return inventoryRepository.save(inventory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Inventory> getInventory(String productId) {
        return inventoryRepository.findAll().stream()
                .filter(inv -> productId.equals(inv.getProductId()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StorageLocation> getLocationsByType(LocationType type) {
        return locationRepository.findAll().stream()
                .filter(loc -> loc.getType() == type)
                .toList();
    }

    @Override
    @Transactional
    public Inventory moveInventory(String productId, String fromLocationId, String toLocationId, int quantityToMove) {
        if (quantityToMove <= 0) {
            throw new IllegalArgumentException("Quantity to move must be positive");
        }
        // check locations exist
        if (!locationRepository.existsById(fromLocationId) || !locationRepository.existsById(toLocationId)) {
            throw new IllegalArgumentException("Location not found");
        }
        // from inventory
        InventoryId fromId = new InventoryId(productId, fromLocationId);
        Inventory fromInv = inventoryRepository.findById(fromId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found at from location"));
        if (fromInv.getQuantity() < quantityToMove) {
            throw new IllegalArgumentException("Insufficient quantity at from location");
        }
        // deduct
        fromInv.setQuantity(fromInv.getQuantity() - quantityToMove);
        inventoryRepository.save(fromInv);
        // to inventory (create if not exists)
        InventoryId toId = new InventoryId(productId, toLocationId);
        Inventory toInv = inventoryRepository.findById(toId)
                .orElse(new Inventory(productId, toLocationId, 0));
        toInv.setQuantity(toInv.getQuantity() + quantityToMove);
        return inventoryRepository.save(toInv);
    }
}
