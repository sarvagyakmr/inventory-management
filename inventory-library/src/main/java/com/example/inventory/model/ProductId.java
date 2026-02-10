package com.example.inventory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductId implements Serializable {

    @Column(name = "sku_id")
    private String skuId;
    @Column(name = "unit_of_measure")
    private String unitOfMeasure;

    public ProductId() {
    }

    public ProductId(String skuId, String unitOfMeasure) {
        this.skuId = skuId;
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId that = (ProductId) o;
        return Objects.equals(skuId, that.skuId) && Objects.equals(unitOfMeasure, that.unitOfMeasure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skuId, unitOfMeasure);
    }
}
