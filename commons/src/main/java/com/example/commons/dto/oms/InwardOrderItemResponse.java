package com.example.commons.dto.oms;

public class InwardOrderItemResponse {
    private Long id;
    private String skuId;
    private int quantity;

    public InwardOrderItemResponse() {}

    public InwardOrderItemResponse(Long id, String skuId, int quantity) {
        this.id = id;
        this.skuId = skuId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
