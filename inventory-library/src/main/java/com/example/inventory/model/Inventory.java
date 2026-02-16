package com.example.inventory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku_id")
    private String skuId;

    @Column(name = "unit_of_measure")
    private String unitOfMeasure;

    @Column(name = "location_id")
    private String locationId;

    @Column(name = "stage_id")
    private Long stageId;

    private int quantity;

    // Association to Product (master by skuId) for separation; inventory held at
    // product level (uom in key)
    @ManyToOne
    @JoinColumn(name = "sku_id", referencedColumnName = "skuId", insertable = false, updatable = false)
    private Product product;

    // Association to Stage
    @ManyToOne
    @JoinColumn(name = "stage_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Stage stage;

    public Inventory() {
    }

    public Inventory(String skuId, String unitOfMeasure, String locationId, Long stageId, int quantity) {
        this.skuId = skuId;
        this.unitOfMeasure = unitOfMeasure;
        this.locationId = locationId;
        this.stageId = stageId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }
}
