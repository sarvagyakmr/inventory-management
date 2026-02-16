package com.example.commons.dto.oms;

import com.example.commons.enums.UnitOfMeasure;

public class GrnItemRequest {
    private String skuId;
    private UnitOfMeasure unitOfMeasure;
    private int quantity;

    public GrnItemRequest() {}

    public GrnItemRequest(String skuId, UnitOfMeasure unitOfMeasure, int quantity) {
        this.skuId = skuId;
        this.unitOfMeasure = unitOfMeasure;
        this.quantity = quantity;
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
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
