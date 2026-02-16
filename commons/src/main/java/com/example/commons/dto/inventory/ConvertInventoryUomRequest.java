package com.example.commons.dto.inventory;

import com.example.commons.enums.UnitOfMeasure;

public class ConvertInventoryUomRequest {
    private String skuId;
    private UnitOfMeasure fromUnitOfMeasure;
    private UnitOfMeasure toUnitOfMeasure;
    private Long stageId;
    private int quantityToConvert;
    private String locationId;
    private String messageId;

    public ConvertInventoryUomRequest() {}

    public ConvertInventoryUomRequest(String skuId, UnitOfMeasure fromUnitOfMeasure, UnitOfMeasure toUnitOfMeasure, Long stageId, int quantityToConvert, String locationId, String messageId) {
        this.skuId = skuId;
        this.fromUnitOfMeasure = fromUnitOfMeasure;
        this.toUnitOfMeasure = toUnitOfMeasure;
        this.stageId = stageId;
        this.quantityToConvert = quantityToConvert;
        this.locationId = locationId;
        this.messageId = messageId;
    }

    public String getSkuId() {
        return skuId;
    }
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
    public UnitOfMeasure getFromUnitOfMeasure() {
        return fromUnitOfMeasure;
    }
    public void setFromUnitOfMeasure(UnitOfMeasure fromUnitOfMeasure) {
        this.fromUnitOfMeasure = fromUnitOfMeasure;
    }
    public UnitOfMeasure getToUnitOfMeasure() {
        return toUnitOfMeasure;
    }
    public void setToUnitOfMeasure(UnitOfMeasure toUnitOfMeasure) {
        this.toUnitOfMeasure = toUnitOfMeasure;
    }
    public Long getStageId() {
        return stageId;
    }
    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }
    public int getQuantityToConvert() {
        return quantityToConvert;
    }
    public void setQuantityToConvert(int quantityToConvert) {
        this.quantityToConvert = quantityToConvert;
    }
    public String getLocationId() {
        return locationId;
    }
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    public String getMessageId() {
        return messageId;
    }
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
