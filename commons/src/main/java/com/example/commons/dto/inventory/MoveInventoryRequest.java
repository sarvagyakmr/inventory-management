package com.example.commons.dto.inventory;

import com.example.commons.enums.UnitOfMeasure;

public class MoveInventoryRequest {
    private String skuId;
    private UnitOfMeasure unitOfMeasure;
    private String fromLocationId;
    private String toLocationId;
    private Long stageId;
    private int quantityToMove;

    public MoveInventoryRequest() {}

    public MoveInventoryRequest(String skuId, UnitOfMeasure unitOfMeasure, String fromLocationId, String toLocationId, Long stageId, int quantityToMove) {
        this.skuId = skuId;
        this.unitOfMeasure = unitOfMeasure;
        this.fromLocationId = fromLocationId;
        this.toLocationId = toLocationId;
        this.stageId = stageId;
        this.quantityToMove = quantityToMove;
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
    public String getFromLocationId() {
        return fromLocationId;
    }
    public void setFromLocationId(String fromLocationId) {
        this.fromLocationId = fromLocationId;
    }
    public String getToLocationId() {
        return toLocationId;
    }
    public void setToLocationId(String toLocationId) {
        this.toLocationId = toLocationId;
    }
    public Long getStageId() {
        return stageId;
    }
    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }
    public int getQuantityToMove() {
        return quantityToMove;
    }
    public void setQuantityToMove(int quantityToMove) {
        this.quantityToMove = quantityToMove;
    }
}
