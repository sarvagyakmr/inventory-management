package com.example.commons.dto.oms;

public class InwardOrderItemRequest {
    private String skuId;
    private int quantity;

    public InwardOrderItemRequest() {}

    public InwardOrderItemRequest(String skuId, int quantity) {
        this.skuId = skuId;
        this.quantity = quantity;
    }

    public String getSkuId() {
        return skuId;
    }
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
