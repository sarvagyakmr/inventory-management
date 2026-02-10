package com.example.inventory.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "uom_conversion")
public class UomConversion {

    @EmbeddedId
    private UomConversionId id;
    private double factor;

    public UomConversion() {
    }

    public UomConversion(String fromSkuId, String fromUnitOfMeasure, String toSkuId, String toUnitOfMeasure, double factor) {
        this.id = new UomConversionId(fromSkuId, fromUnitOfMeasure, toSkuId, toUnitOfMeasure);
        this.factor = factor;
    }

    public UomConversionId getId() {
        return id;
    }

    public void setId(UomConversionId id) {
        this.id = id;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }
}