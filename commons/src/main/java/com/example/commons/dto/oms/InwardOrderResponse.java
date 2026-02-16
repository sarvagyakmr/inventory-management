package com.example.commons.dto.oms;

import com.example.commons.enums.OrderStatus;
import java.util.List;

public class InwardOrderResponse {
    private Long id;
    private Long supplierId;
    private OrderStatus status;
    private String locationId;
    private List<InwardOrderItemResponse> items;

    public InwardOrderResponse() {}

    public InwardOrderResponse(Long id, Long supplierId, OrderStatus status, String locationId, List<InwardOrderItemResponse> items) {
        this.id = id;
        this.supplierId = supplierId;
        this.status = status;
        this.locationId = locationId;
        this.items = items;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public String getLocationId() {
        return locationId;
    }
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    public List<InwardOrderItemResponse> getItems() {
        return items;
    }
    public void setItems(List<InwardOrderItemResponse> items) {
        this.items = items;
    }
}
