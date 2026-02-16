package com.example.commons.dto.wms;

public class AisleRequest {
    private Long storageAreaId;

    public AisleRequest() {}

    public AisleRequest(Long storageAreaId) {
        this.storageAreaId = storageAreaId;
    }

    public Long getStorageAreaId() {
        return storageAreaId;
    }
    public void setStorageAreaId(Long storageAreaId) {
        this.storageAreaId = storageAreaId;
    }
}
