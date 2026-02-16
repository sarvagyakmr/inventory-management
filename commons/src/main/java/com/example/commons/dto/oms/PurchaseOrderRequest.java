package com.example.commons.dto.oms;

import java.util.List;

public class PurchaseOrderRequest {
    private Long customerId;
    private String locationId;
    private List<PurchaseOrderItemRequest> items;

    public PurchaseOrderRequest() {}

    public PurchaseOrderRequest(Long customerId, String locationId, List<PurchaseOrderItemRequest> items) {
        this.customerId = customerId;
        this.locationId = locationId;
        this.items = items;
    }

    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getLocationId() {
        return locationId;
    }
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    public List<PurchaseOrderItemRequest> getItems() {
        return items;
    }
    public void setItems(List<PurchaseOrderItemRequest> items) {
        this.items = items;
    }
}
