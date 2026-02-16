package com.example.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Location entity in warehouse (detailed storage location).
 * id: generated as {aisleId}-L{locationNumber} e.g. S1-A3-L4
 * Input: storageAreaId, aisleId
 * type: LocationCapacityType (SINGLE, MULTI)
 * Note: Separate from StorageLocation which is used for inventory placement.
 */
@Entity
@Table(name = "location")
public class Location {

    @Id
    private String id;

    private Long storageAreaId;

    private String aisleId;

    @Enumerated(EnumType.STRING)
    private LocationCapacityType type;

    public Location() {
    }

    /**
     * Constructor with type for creation.
     */
    public Location(String id, Long storageAreaId, String aisleId, LocationCapacityType type) {
        this.id = id;
        this.storageAreaId = storageAreaId;
        this.aisleId = aisleId;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getStorageAreaId() {
        return storageAreaId;
    }

    public void setStorageAreaId(Long storageAreaId) {
        this.storageAreaId = storageAreaId;
    }

    public String getAisleId() {
        return aisleId;
    }

    public void setAisleId(String aisleId) {
        this.aisleId = aisleId;
    }

    public LocationCapacityType getType() {
        return type;
    }

    public void setType(LocationCapacityType type) {
        this.type = type;
    }
}