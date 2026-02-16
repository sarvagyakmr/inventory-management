package com.example.commons.dto.inventory;

import com.example.commons.enums.UnitOfMeasure;

public class ProductUomRequest {
    private String skuId;
    private UnitOfMeasure unitOfMeasure;

    public ProductUomRequest() {}

    public ProductUomRequest(String skuId, UnitOfMeasure unitOfMeasure) {
        this.skuId = skuId;
        this.unitOfMeasure = unitOfMeasure;
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
}
