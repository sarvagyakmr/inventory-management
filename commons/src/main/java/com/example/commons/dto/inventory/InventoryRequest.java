package com.example.commons.dto.inventory;

import com.example.commons.enums.UnitOfMeasure;

public class InventoryRequest {
    private String skuId;
    private UnitOfMeasure unitOfMeasure;
    private String locationId;
    private Long stageId;

    public InventoryRequest() {}

    public InventoryRequest(String skuId, UnitOfMeasure unitOfMeasure, String locationId, Long stageId) {
        this.skuId = skuId;
        this.unitOfMeasure = unitOfMeasure;
        this.locationId = locationId;
        this.stageId = stageId;
    }

    public String getSkuId() {
        return skuId;
    }
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }
    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
    public String getLocationId() {
        return locationId;
    }
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    public Long getStageId() {
        return stageId;
    }
    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }
}
