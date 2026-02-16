package com.example.commons.dto.wms;

import com.example.commons.enums.ItemStatus;
import com.example.commons.enums.QcStatus;

public class ItemResponse {
    private Long id;
    private String productId;
    private QcStatus qcStatus;
    private ItemStatus itemStatus;

    public ItemResponse() {}

    public ItemResponse(Long id, String productId, QcStatus qcStatus, ItemStatus itemStatus) {
        this.id = id;
        this.productId = productId;
        this.qcStatus = qcStatus;
        this.itemStatus = itemStatus;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public ItemStatus getItemStatus() {
        return itemStatus;
    }
    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }
}
