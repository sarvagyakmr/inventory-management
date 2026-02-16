package com.example.commons.dto.inventory;

import com.example.commons.enums.UnitOfMeasure;

public class UomConversionRequest {
    private String skuId;
    private UnitOfMeasure fromUnitOfMeasure;
    private UnitOfMeasure toUnitOfMeasure;
    private Double conversionFactor;

    public UomConversionRequest() {}

    public UomConversionRequest(String skuId, UnitOfMeasure fromUnitOfMeasure, UnitOfMeasure toUnitOfMeasure, Double conversionFactor) {
        this.skuId = skuId;
        this.fromUnitOfMeasure = fromUnitOfMeasure;
        this.toUnitOfMeasure = toUnitOfMeasure;
        this.conversionFactor = conversionFactor;
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
    public Double getConversionFactor() {
        return conversionFactor;
    }
    public void setConversionFactor(Double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }
}
