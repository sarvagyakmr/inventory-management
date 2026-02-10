package com.example.inventory.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UomConversionId implements Serializable {

    private String fromSkuId;
    private String fromUnitOfMeasure;
    private String toSkuId;
    private String toUnitOfMeasure;

    public UomConversionId() {
    }

    public UomConversionId(String fromSkuId, String fromUnitOfMeasure, String toSkuId, String toUnitOfMeasure) {
        this.fromSkuId = fromSkuId;
        this.fromUnitOfMeasure = fromUnitOfMeasure;
        this.toSkuId = toSkuId;
        this.toUnitOfMeasure = toUnitOfMeasure;
    }

    public String getFromSkuId() {
        return fromSkuId;
    }

    public void setFromSkuId(String fromSkuId) {
        this.fromSkuId = fromSkuId;
    }

    public String getFromUnitOfMeasure() {
        return fromUnitOfMeasure;
    }

    public void setFromUnitOfMeasure(String fromUnitOfMeasure) {
        this.fromUnitOfMeasure = fromUnitOfMeasure;
    }

    public String getToSkuId() {
        return toSkuId;
    }

    public void setToSkuId(String toSkuId) {
        this.toSkuId = toSkuId;
    }

    public String getToUnitOfMeasure() {
        return toUnitOfMeasure;
    }

    public void setToUnitOfMeasure(String toUnitOfMeasure) {
        this.toUnitOfMeasure = toUnitOfMeasure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UomConversionId that = (UomConversionId) o;
        return Objects.equals(fromSkuId, that.fromSkuId) && Objects.equals(fromUnitOfMeasure, that.fromUnitOfMeasure) && Objects.equals(toSkuId, that.toSkuId) && Objects.equals(toUnitOfMeasure, that.toUnitOfMeasure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromSkuId, fromUnitOfMeasure, toSkuId, toUnitOfMeasure);
    }
}