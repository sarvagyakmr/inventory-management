package com.example.inventory.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_uom")
public class ProductUom {

    @EmbeddedId
    private ProductId id;

    public ProductUom() {
    }

    public ProductUom(String skuId, String unitOfMeasure) {
        this.id = new ProductId(skuId, unitOfMeasure);
    }

    public ProductId getId() {
        return id;
    }

    public void setId(ProductId id) {
        this.id = id;
    }

    public String getSkuId() {
        return id != null ? id.getSkuId() : null;
    }

    public String getUnitOfMeasure() {
        return id != null ? id.getUnitOfMeasure() : null;
    }
}