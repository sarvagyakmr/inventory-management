package com.example.commons.dto.wms;

import com.example.commons.enums.StorageAreaType;

public class StorageAreaResponse {
    private Long id;
    private String description;
    private StorageAreaType type;

    public StorageAreaResponse() {}

    public StorageAreaResponse(Long id, String description, StorageAreaType type) {
        this.id = id;
        this.description = description;
        this.type = type;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
