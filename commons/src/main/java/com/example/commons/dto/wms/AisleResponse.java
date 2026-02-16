package com.example.commons.dto.wms;

public class AisleResponse {
    private String id;
    private Long storageAreaId;

    public AisleResponse() {}

    public AisleResponse(String id, Long storageAreaId) {
        this.id = id;
        this.storageAreaId = storageAreaId;
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
}
