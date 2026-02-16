package com.example.commons.dto.wms;

import com.example.commons.enums.LocationCapacityType;

public class LocationResponse {
    private String id;
    private Long storageAreaId;
    private String aisleId;
    private LocationCapacityType type;
    private String uId;

    public LocationResponse() {}

    public LocationResponse(String id, Long storageAreaId, String aisleId, LocationCapacityType type, String uId) {
        this.id = id;
        this.storageAreaId = storageAreaId;
        this.aisleId = aisleId;
        this.type = type;
        this.uId = uId;
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
    public String getuId() {
        return uId;
    }
    public void setuId(String uId) {
        this.uId = uId;
    }
}
