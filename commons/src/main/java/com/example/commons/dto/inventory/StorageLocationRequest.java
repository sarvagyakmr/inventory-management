package com.example.commons.dto.inventory;

import com.example.commons.enums.LocationType;

public class StorageLocationRequest {
    private LocationType type;
    private String description;

    public StorageLocationRequest() {}

    public StorageLocationRequest(LocationType type, String description) {
        this.type = type;
        this.description = description;
    }

    public LocationType getType() {
        return type;
    }
    public void setType(LocationType type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
