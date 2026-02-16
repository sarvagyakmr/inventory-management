package com.example.commons.dto.wms;

import com.example.commons.enums.QcStatus;
import com.example.commons.enums.StorageAreaType;
import java.util.List;

public class BoxResponse {
    private Long id;
    private StorageAreaType type;
    private StorageAreaType status;
    private QcStatus qcStatus;
    private String locationId;
    private Long orderId;
    private List<ItemResponse> items;

    public BoxResponse() {}

    public BoxResponse(Long id, StorageAreaType type, StorageAreaType status, QcStatus qcStatus, String locationId, Long orderId, List<ItemResponse> items) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.qcStatus = qcStatus;
        this.locationId = locationId;
        this.orderId = orderId;
        this.items = items;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public StorageAreaType getType() {
        return type;
    }
    public void setType(StorageAreaType type) {
        this.type = type;
    }
    public StorageAreaType getStatus() {
        return status;
    }
    public void setStatus(StorageAreaType status) {
        this.status = status;
    }
    public QcStatus getQcStatus() {
        return qcStatus;
    }
    public void setQcStatus(QcStatus qcStatus) {
        this.qcStatus = qcStatus;
    }
    public String getLocationId() {
        return locationId;
    }
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public List<ItemResponse> getItems() {
        return items;
    }
    public void setItems(List<ItemResponse> items) {
        this.items = items;
    }
}
