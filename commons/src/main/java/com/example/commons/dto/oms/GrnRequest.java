package com.example.commons.dto.oms;

import java.util.List;

public class GrnRequest {
    private Long orderId;
    private List<GrnItemRequest> items;

    public GrnRequest() {}

    public GrnRequest(Long orderId, List<GrnItemRequest> items) {
        this.orderId = orderId;
        this.items = items;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public List<GrnItemRequest> getItems() {
        return items;
    }
    public void setItems(List<GrnItemRequest> items) {
        this.items = items;
    }
}
