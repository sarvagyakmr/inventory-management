package com.example.commons.dto.oms;

import com.example.commons.enums.FulfillableStatus;
import java.util.List;

public class PurchaseOrderResponse {
    private Long id;
    private Long customerId;
    private String locationId;
    private FulfillableStatus fulfillableStatus;
    private List<PurchaseOrderItemResponse> items;

    public PurchaseOrderResponse() {}

    public PurchaseOrderResponse(Long id, Long customerId, String locationId, FulfillableStatus fulfillableStatus, List<PurchaseOrderItemResponse> items) {
        this.id = id;
        this.customerId = customerId;
        this.locationId = locationId;
        this.fulfillableStatus = fulfillableStatus;
        this.items = items;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public FulfillableStatus getFulfillableStatus() {
        return fulfillableStatus;
    }
    public void setFulfillableStatus(FulfillableStatus fulfillableStatus) {
        this.fulfillableStatus = fulfillableStatus;
    }
    public List<PurchaseOrderItemResponse> getItems() {
        return items;
    }
    public void setItems(List<PurchaseOrderItemResponse> items) {
        this.items = items;
    }
}
