package com.example.commons.dto.wms;

public class BoxLocationRequest {
    private String locationId;

    public BoxLocationRequest() {}

    public BoxLocationRequest(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationId() {
        return locationId;
    }
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
