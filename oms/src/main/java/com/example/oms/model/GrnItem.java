package com.example.oms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "grn_item")
public class GrnItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuId;
    private String unitOfMeasure;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "grn_id")
    @JsonIgnore
    private Grn grn;

    public GrnItem() {
    }

    public GrnItem(String skuId, String unitOfMeasure, int quantity) {
        this.skuId = skuId;
        this.unitOfMeasure = unitOfMeasure;
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

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Grn getGrn() {
        return grn;
    }

    public void setGrn(Grn grn) {
        this.grn = grn;
    }
}
