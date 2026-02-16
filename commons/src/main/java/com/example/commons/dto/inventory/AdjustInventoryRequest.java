package com.example.commons.dto.inventory;

import com.example.commons.enums.UnitOfMeasure;

public class AdjustInventoryRequest {
    private String skuId;
    private UnitOfMeasure unitOfMeasure;
    private String locationId;
    private Long stageId;
    private int quantityChange;
    private String messageId;

    public AdjustInventoryRequest() {}

    public AdjustInventoryRequest(String skuId, UnitOfMeasure unitOfMeasure, String locationId, Long stageId, int quantityChange, String messageId) {
        this.skuId = skuId;
        this.unitOfMeasure = unitOfMeasure;
        this.locationId = locationId;
        this.stageId = stageId;
        this.quantityChange = quantityChange;
        this.messageId = messageId;
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
    public int getQuantityChange() {
        return quantityChange;
    }
    public void setQuantityChange(int quantityChange) {
        this.quantityChange = quantityChange;
    }
    public String getMessageId() {
        return messageId;
    }
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
