package com.example.wms.service;

import com.example.commons.dto.wms.*;
import com.example.commons.enums.LocationCapacityType;
import com.example.commons.enums.QcStatus;
import com.example.commons.enums.StorageAreaType;
import com.example.wms.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseDtoService {

    private final WarehouseService warehouseService;

    public WarehouseDtoService(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public StorageAreaResponse createStorageArea(StorageAreaRequest request) {
        StorageArea area = warehouseService.createStorageArea(request.getDescription(), request.getType());
        return mapToStorageAreaResponse(area);
    }

    public AisleResponse createAisle(AisleRequest request) {
        Aisle aisle = warehouseService.createAisle(request.getStorageAreaId());
        return mapToAisleResponse(aisle);
    }

    public LocationResponse createLocation(LocationRequest request) {
        Location location = warehouseService.createLocation(request.getStorageAreaId(), request.getAisleId(),
                request.getType());
        return mapToLocationResponse(location);
    }

    public BoxResponse createBox(BoxRequest request) {
        Box box = warehouseService.createBox(request.getType(), request.getQcStatus(), request.getOrderId());
        return mapToBoxResponse(box);
    }

    public BoxResponse updateBoxLocation(Long boxId, BoxLocationRequest request) {
        Box box = warehouseService.updateBoxLocation(boxId, request.getLocationId());
        return mapToBoxResponse(box);
    }

    public ItemResponse createItem(ItemRequest request) {
        Item item = warehouseService.createItem(request.getProductId(), request.getQcStatus());
        return mapToItemResponse(item);
    }

    public BoxResponse addItemToBox(Long boxId, AddItemToBoxRequest request) {
        Box box = warehouseService.addItemToBox(request.getItemId(), boxId);
        return mapToBoxResponse(box);
    }

    // Mappers

    private StorageAreaResponse mapToStorageAreaResponse(StorageArea area) {
        return new StorageAreaResponse(area.getId(), area.getDescription(), area.getType());
    }

    private AisleResponse mapToAisleResponse(Aisle aisle) {
        return new AisleResponse(aisle.getId(), aisle.getStorageAreaId());
        // Aisle.getId() returns String?
        // Aisle entity was not viewed, but Location has aisleId as String.
        // Assuming Aisle model has String ID.
    }

    private LocationResponse mapToLocationResponse(Location location) {
        return new LocationResponse(location.getId(), location.getStorageAreaId(), location.getAisleId(),
                location.getType(), location.getId()); // uId is same as id? id is uId. Location has id field.
    }

    private BoxResponse mapToBoxResponse(Box box) {
        String locationId = box.getLocation() != null ? box.getLocation().getId() : null;
        List<ItemResponse> itemResponses = box.getItems().stream()
                .map(this::mapToItemResponse)
                .collect(Collectors.toList());
        return new BoxResponse(
                box.getId(),
                box.getType(),
                box.getStatus(),
                box.getQcStatus(),
                locationId,
                box.getOrderId(),
                itemResponses);
    }

    private ItemResponse mapToItemResponse(Item item) {
        return new ItemResponse(item.getId(), item.getProductId(), item.getQcStatus(), item.getItemStatus());
    }
}
