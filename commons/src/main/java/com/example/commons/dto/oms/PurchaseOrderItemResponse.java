package com.example.commons.dto.oms;

import com.example.commons.enums.UnitOfMeasure;

public class PurchaseOrderItemResponse {
    private Long id;
    private String skuId;
    private UnitOfMeasure unitOfMeasure;
    private int quantity;

    public PurchaseOrderItemResponse() {}

    public PurchaseOrderItemResponse(Long id, String skuId, UnitOfMeasure unitOfMeasure, int quantity) {
        this.id = id;
        this.skuId = skuId;
        this.unitOfMeasure = unitOfMeasure;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
