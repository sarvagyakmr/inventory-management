package com.example.commons.dto.inventory;

public class ProductResponse {
    private String skuId;

    public ProductResponse() {}

    public ProductResponse(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuId() {
        return skuId;
    }
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
}
