package com.example.inventory.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

    @EmbeddedId
    private InventoryId id;
    private int quantity;

    public Inventory() {
    }

    public Inventory(String productId, String locationId, int quantity) {
        this.id = new InventoryId(productId, locationId);
        this.quantity = quantity;
    }

    public InventoryId getId() {
        return id;
    }

    public void setId(InventoryId id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return id != null ? id.getProductId() : null;
    }

    public String getLocationId() {
        return id != null ? id.getLocationId() : null;
    }
}
