package com.example.commons.dto.oms;

import java.util.List;

public class InwardOrderRequest {
    private Long supplierId;
    private String locationId;
    private List<InwardOrderItemRequest> items;

    public InwardOrderRequest() {}

    public InwardOrderRequest(Long supplierId, String locationId, List<InwardOrderItemRequest> items) {
        this.supplierId = supplierId;
        this.locationId = locationId;
        this.items = items;
    }

    public Long getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
    public String getLocationId() {
        return locationId;
    }
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    public List<InwardOrderItemRequest> getItems() {
        return items;
    }
    public void setItems(List<InwardOrderItemRequest> items) {
        this.items = items;
    }
}
