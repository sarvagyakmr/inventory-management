package com.example.commons.dto.inventory;

import com.example.commons.enums.UnitOfMeasure;

public class TransitionInventoryRequest {
    private String skuId;
    private UnitOfMeasure unitOfMeasure;
    private String locationId;
    private Long fromStageId;
    private Long toStageId;
    private int quantityToMove;
    private String messageId;

    public TransitionInventoryRequest() {}

    public TransitionInventoryRequest(String skuId, UnitOfMeasure unitOfMeasure, String locationId, Long fromStageId, Long toStageId, int quantityToMove, String messageId) {
        this.skuId = skuId;
        this.unitOfMeasure = unitOfMeasure;
        this.locationId = locationId;
        this.fromStageId = fromStageId;
        this.toStageId = toStageId;
        this.quantityToMove = quantityToMove;
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
    public Long getFromStageId() {
        return fromStageId;
    }
    public void setFromStageId(Long fromStageId) {
        this.fromStageId = fromStageId;
    }
    public Long getToStageId() {
        return toStageId;
    }
    public void setToStageId(Long toStageId) {
        this.toStageId = toStageId;
    }
    public int getQuantityToMove() {
        return quantityToMove;
    }
    public void setQuantityToMove(int quantityToMove) {
        this.quantityToMove = quantityToMove;
    }
    public String getMessageId() {
        return messageId;
    }
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
