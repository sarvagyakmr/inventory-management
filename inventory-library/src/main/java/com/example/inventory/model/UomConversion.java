package com.example.inventory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.example.commons.enums.UnitOfMeasure;

@Entity
@Table(name = "uom_conversion")
public class UomConversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_sku_id")
    private String fromSkuId;

    @Enumerated(EnumType.STRING)
    @Column(name = "from_unit_of_measure")
    private UnitOfMeasure fromUnitOfMeasure;

    @Column(name = "to_sku_id")
    private String toSkuId;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_unit_of_measure")
    private UnitOfMeasure toUnitOfMeasure;

    private double factor;

    public UomConversion() {
    }

    public UomConversion(String fromSkuId, UnitOfMeasure fromUnitOfMeasure, String toSkuId,
            UnitOfMeasure toUnitOfMeasure,
            double factor) {
        this.fromSkuId = fromSkuId;
        this.fromUnitOfMeasure = fromUnitOfMeasure;
        this.toSkuId = toSkuId;
        this.toUnitOfMeasure = toUnitOfMeasure;
        this.factor = factor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromSkuId() {
        return fromSkuId;
    }

    public void setFromSkuId(String fromSkuId) {
        this.fromSkuId = fromSkuId;
    }

    public UnitOfMeasure getFromUnitOfMeasure() {
        return fromUnitOfMeasure;
    }

    public void setFromUnitOfMeasure(UnitOfMeasure fromUnitOfMeasure) {
        this.fromUnitOfMeasure = fromUnitOfMeasure;
    }

    public String getToSkuId() {
        return toSkuId;
    }

    public void setToSkuId(String toSkuId) {
        this.toSkuId = toSkuId;
    }

    public UnitOfMeasure getToUnitOfMeasure() {
        return toUnitOfMeasure;
    }

    public void setToUnitOfMeasure(UnitOfMeasure toUnitOfMeasure) {
        this.toUnitOfMeasure = toUnitOfMeasure;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }
}