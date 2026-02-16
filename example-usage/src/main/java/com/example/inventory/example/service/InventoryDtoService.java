package com.example.inventory.example.service;

import com.example.commons.dto.inventory.*;
import com.example.commons.enums.LocationType;
import com.example.commons.enums.UnitOfMeasure;
import com.example.inventory.model.*;
import com.example.inventory.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryDtoService {

    private final InventoryService inventoryService;

    public InventoryDtoService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public StorageLocationResponse addStorageLocation(StorageLocationRequest request) {
        StorageLocation location = inventoryService.addStorageLocation(request.getType(), request.getDescription());
        return mapToStorageLocationResponse(location);
    }

    public StageResponse addStage(StageRequest request) {
        Stage stage = inventoryService.addStage(request.getName(), request.getDescription());
        return mapToStageResponse(stage);
    }

    public StageTransitionResponse addStageTransition(StageTransitionRequest request) {
        StageTransition transition = inventoryService.addStageTransition(request.getFromStageId(),
                request.getToStageId());
        return mapToStageTransitionResponse(transition);
    }

    public ProductResponse addProduct(ProductRequest request) {
        Product product = inventoryService.addProduct(request.getSkuId());
        return mapToProductResponse(product);
    }

    public ProductUomResponse addUom(ProductUomRequest request) {
        ProductUom productUom = inventoryService.addUom(request.getSkuId(), request.getUnitOfMeasure());
        return mapToProductUomResponse(productUom);
    }

    public UomConversionResponse addConversion(UomConversionRequest request) {
        UomConversion conversion = inventoryService.addConversion(request.getSkuId(), request.getFromUnitOfMeasure(),
                request.getToUnitOfMeasure(), request.getConversionFactor());
        return mapToUomConversionResponse(conversion);
    }

    public InventoryResponse addInventory(InventoryRequest request) {
        Inventory inventory = inventoryService.addInventory(request.getSkuId(), request.getUnitOfMeasure(),
                request.getLocationId(), request.getStageId());
        return mapToInventoryResponse(inventory);
    }

    public InventoryResponse adjustQuantity(AdjustInventoryRequest request) {
        Inventory inventory = inventoryService.adjustQuantity(request.getSkuId(), request.getUnitOfMeasure(),
                request.getLocationId(), request.getStageId(), request.getQuantityChange(), request.getMessageId());
        return mapToInventoryResponse(inventory);
    }

    public List<InventoryResponse> getInventory(String skuId, UnitOfMeasure unitOfMeasure) {
        List<Inventory> inventories = inventoryService.getInventory(skuId, unitOfMeasure);
        return inventories.stream().map(this::mapToInventoryResponse).collect(Collectors.toList());
    }

    public List<StorageLocationResponse> getLocationsByType(LocationType type) {
        List<StorageLocation> locations = inventoryService.getLocationsByType(type);
        return locations.stream().map(this::mapToStorageLocationResponse).collect(Collectors.toList());
    }

    public InventoryResponse moveInventory(MoveInventoryRequest request) {
        Inventory inventory = inventoryService.moveInventory(request.getSkuId(), request.getUnitOfMeasure(),
                request.getFromLocationId(), request.getToLocationId(), request.getStageId(),
                request.getQuantityToMove());
        return mapToInventoryResponse(inventory);
    }

    public InventoryResponse convertUom(ConvertInventoryUomRequest request) {
        Inventory inventory = inventoryService.convertUom(request.getSkuId(), request.getFromUnitOfMeasure(),
                request.getToUnitOfMeasure(), request.getStageId(), request.getQuantityToConvert(),
                request.getLocationId(), request.getMessageId());
        return mapToInventoryResponse(inventory);
    }

    public InventoryResponse moveToStage(TransitionInventoryRequest request) {
        Inventory inventory = inventoryService.moveToStage(request.getSkuId(), request.getUnitOfMeasure(),
                request.getLocationId(), request.getFromStageId(), request.getToStageId(), request.getQuantityToMove(),
                request.getMessageId());
        return mapToInventoryResponse(inventory);
    }

    // Helper methods for mapping
    private StorageLocationResponse mapToStorageLocationResponse(StorageLocation location) {
        return new StorageLocationResponse(location.getId(), location.getType(), location.getDescription());
    }

    private StageResponse mapToStageResponse(Stage stage) {
        return new StageResponse(stage.getId(), stage.getName(), stage.getDescription());
    }

    private StageTransitionResponse mapToStageTransitionResponse(StageTransition transition) {
        return new StageTransitionResponse(transition.getId(), transition.getFromStageId(),
                transition.getToStageId());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(product.getSkuId());
    }

    private ProductUomResponse mapToProductUomResponse(ProductUom productUom) {
        return new ProductUomResponse(productUom.getId(), productUom.getSkuId(),
                productUom.getUnitOfMeasure());
    }

    private UomConversionResponse mapToUomConversionResponse(UomConversion conversion) {
        return new UomConversionResponse(conversion.getId(), conversion.getFromSkuId(), // Use direct fields if
                                                                                        // available, UomConversion has
                                                                                        // getFromSkuId
                conversion.getFromUnitOfMeasure(), conversion.getToUnitOfMeasure(), conversion.getFactor());
    }

    private InventoryResponse mapToInventoryResponse(Inventory inventory) {
        return new InventoryResponse(inventory.getId(), inventory.getSkuId(), inventory.getUnitOfMeasure(),
                inventory.getLocationId(), inventory.getStageId(), inventory.getQuantity());
    }
}
