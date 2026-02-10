package com.example.inventory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InventoryId implements Serializable {

    @Column(name = "sku_id")
    private String skuId;
    @Column(name = "unit_of_measure")
    private String unitOfMeasure;
    @Column(name = "location_id")
    private String locationId;
    @Column(name = "stage_id")
    private String stageId;

    public InventoryId() {
    }

    public InventoryId(String skuId, String unitOfMeasure, String locationId, String stageId) {
        this.skuId = skuId;
        this.unitOfMeasure = unitOfMeasure;
        this.locationId = locationId;
        this.stageId = stageId;
    }

    // overload for backward compatibility in location-only moves (stage remains same)
    public InventoryId(String skuId, String unitOfMeasure, String locationId) {
        this(skuId, unitOfMeasure, locationId, null);
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

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryId that = (InventoryId) o;
        return Objects.equals(skuId, that.skuId) && Objects.equals(unitOfMeasure, that.unitOfMeasure) && Objects.equals(locationId, that.locationId) && Objects.equals(stageId, that.stageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skuId, unitOfMeasure, locationId, stageId);
    }
}
