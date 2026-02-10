package com.example.inventory.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

    @EmbeddedId
    private InventoryId id;
    private int quantity;

    // Association to Product (master by skuId) for separation; inventory held at product level (uom in key)
    @ManyToOne
    @JoinColumn(name = "sku_id", referencedColumnName = "skuId", insertable = false, updatable = false)
    private Product product;

    // Association to Stage
    @ManyToOne
    @JoinColumn(name = "stage_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Stage stage;

    public Inventory() {
    }

    public Inventory(String skuId, String unitOfMeasure, String locationId, String stageId, int quantity) {
        this.id = new InventoryId(skuId, unitOfMeasure, locationId, stageId);
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getSkuId() {
        return id != null ? id.getSkuId() : null;
    }

    public String getUnitOfMeasure() {
        return id != null ? id.getUnitOfMeasure() : null;
    }

    public String getLocationId() {
        return id != null ? id.getLocationId() : null;
    }

    public String getStageId() {
        return id != null ? id.getStageId() : null;
    }
}
