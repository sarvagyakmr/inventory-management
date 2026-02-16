package com.example.commons.dto.wms;

import com.example.commons.enums.StorageAreaType;

public class StorageAreaRequest {
    private String description;
    private StorageAreaType type;

    public StorageAreaRequest() {}

    public StorageAreaRequest(String description, StorageAreaType type) {
        this.description = description;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public StorageAreaType getType() {
        return type;
    }
    public void setType(StorageAreaType type) {
        this.type = type;
    }
}
