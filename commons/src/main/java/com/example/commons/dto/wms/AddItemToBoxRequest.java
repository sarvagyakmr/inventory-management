package com.example.commons.dto.wms;

public class AddItemToBoxRequest {
    private Long itemId;

    public AddItemToBoxRequest() {}

    public AddItemToBoxRequest(Long itemId) {
        this.itemId = itemId;
    }

    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
