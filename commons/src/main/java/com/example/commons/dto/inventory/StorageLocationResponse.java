package com.example.commons.dto.inventory;

import com.example.commons.enums.LocationType;

public class StorageLocationResponse {
    private String id;
    private LocationType type;
    private String description;

    public StorageLocationResponse() {}

    public StorageLocationResponse(String id, LocationType type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
