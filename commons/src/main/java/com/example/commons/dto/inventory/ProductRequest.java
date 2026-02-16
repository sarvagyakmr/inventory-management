package com.example.commons.dto.inventory;

public class ProductRequest {
    private String skuId;

    public ProductRequest() {}

    public ProductRequest(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuId() {
        return skuId;
    }
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
}
