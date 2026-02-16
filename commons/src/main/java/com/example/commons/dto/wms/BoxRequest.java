package com.example.commons.dto.wms;

import com.example.commons.enums.QcStatus;
import com.example.commons.enums.StorageAreaType;

public class BoxRequest {
    private StorageAreaType type;
    private QcStatus qcStatus;
    private Long orderId;

    public BoxRequest() {}

    public BoxRequest(StorageAreaType type, QcStatus qcStatus, Long orderId) {
        this.type = type;
        this.qcStatus = qcStatus;
        this.orderId = orderId;
    }

    public StorageAreaType getType() {
        return type;
    }
    public void setType(StorageAreaType type) {
        this.type = type;
    }
    public QcStatus getQcStatus() {
        return qcStatus;
    }
    public void setQcStatus(QcStatus qcStatus) {
        this.qcStatus = qcStatus;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
