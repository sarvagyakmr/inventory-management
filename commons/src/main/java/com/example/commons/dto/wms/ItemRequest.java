package com.example.commons.dto.wms;

import com.example.commons.enums.QcStatus;

public class ItemRequest {
    private String productId;
    private QcStatus qcStatus;

    public ItemRequest() {}

    public ItemRequest(String productId, QcStatus qcStatus) {
        this.productId = productId;
        this.qcStatus = qcStatus;
    }

    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public QcStatus getQcStatus() {
        return qcStatus;
    }
    public void setQcStatus(QcStatus qcStatus) {
        this.qcStatus = qcStatus;
    }
}
