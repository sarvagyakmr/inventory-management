package com.example.inventory.service;

import com.example.inventory.model.Inventory;
import com.example.inventory.model.LocationType;
import com.example.inventory.model.Product;
import com.example.inventory.model.ProductUom;
import com.example.inventory.model.Stage;
import com.example.inventory.model.StageTransition;
import com.example.inventory.model.StorageLocation;
import com.example.inventory.model.UomConversion;

import java.util.List;

public interface InventoryService {
    StorageLocation addStorageLocation(LocationType type, String description);

    Stage addStage(String name, String description);

    StageTransition addStageTransition(Long fromStageId, Long toStageId);

    Product addProduct(String skuId);

    ProductUom addUom(String skuId, String unitOfMeasure);

    // conversion only within same product's UOMs
    UomConversion addConversion(String skuId, String fromUnitOfMeasure, String toUnitOfMeasure, double factor);

    Inventory addInventory(String skuId, String unitOfMeasure, String locationId, Long stageId);

    // messageId ensures idempotency (duplicate ignored)
    Inventory adjustQuantity(String skuId, String unitOfMeasure, String locationId, Long stageId, int quantityChange,
            String messageId);

    List<Inventory> getInventory(String skuId, String unitOfMeasure);

    List<StorageLocation> getLocationsByType(LocationType type);

    Inventory moveInventory(String skuId, String unitOfMeasure, String fromLocationId, String toLocationId,
            Long stageId, int quantityToMove);

    // messageId ensures idempotency (duplicate ignored)
    Inventory convertUom(String skuId, String fromUnitOfMeasure, String toUnitOfMeasure, Long stageId,
            int quantityToConvert, String locationId, String messageId);

    // move to new stage (checks allowed transition)
    Inventory moveToStage(String skuId, String unitOfMeasure, String locationId, Long fromStageId, Long toStageId,
            int quantityToMove, String messageId);

    /**
     * Get Product by skuId. Used by OMS as source of truth for products.
     * 
     * @param skuId product SKU
     * @return Product
     * @throws IllegalArgumentException if not found
     */
    Product getProduct(String skuId);
}
