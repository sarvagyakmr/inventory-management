package com.example.commons.dto.inventory;

import com.example.commons.enums.UnitOfMeasure;

public class ProductUomResponse {
    private Long id;
    private String skuId;
    private UnitOfMeasure unitOfMeasure;

    public ProductUomResponse() {}

    public ProductUomResponse(Long id, String skuId, UnitOfMeasure unitOfMeasure) {
        this.id = id;
        this.skuId = skuId;
        this.unitOfMeasure = unitOfMeasure;
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
}
