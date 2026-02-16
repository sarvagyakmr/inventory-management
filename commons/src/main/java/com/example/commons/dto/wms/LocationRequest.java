package com.example.commons.dto.wms;

import com.example.commons.enums.LocationCapacityType;

public class LocationRequest {
    private Long storageAreaId;
    private String aisleId;
    private LocationCapacityType type;

    public LocationRequest() {}

    public LocationRequest(Long storageAreaId, String aisleId, LocationCapacityType type) {
        this.storageAreaId = storageAreaId;
        this.aisleId = aisleId;
        this.type = type;
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
