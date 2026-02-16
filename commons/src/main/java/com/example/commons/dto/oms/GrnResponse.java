package com.example.commons.dto.oms;

import java.time.LocalDateTime;
import java.util.List;

public class GrnResponse {
    private Long id;
    private Long orderId;
    private LocalDateTime receivedDate;
    private List<GrnItemResponse> items;

    public GrnResponse() {}

    public GrnResponse(Long id, Long orderId, LocalDateTime receivedDate, List<GrnItemResponse> items) {
        this.id = id;
        this.orderId = orderId;
        this.receivedDate = receivedDate;
        this.items = items;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public LocalDateTime getReceivedDate() {
        return receivedDate;
    }
    public void setReceivedDate(LocalDateTime receivedDate) {
        this.receivedDate = receivedDate;
    }
    public List<GrnItemResponse> getItems() {
        return items;
    }
    public void setItems(List<GrnItemResponse> items) {
        this.items = items;
    }
}
