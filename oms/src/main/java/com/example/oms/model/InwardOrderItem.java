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
@Table(name = "inward_order_item")
public class InwardOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuId;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private InwardOrder order;

    public InwardOrderItem() {
    }

    public InwardOrderItem(String skuId, int quantity) {
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

    public InwardOrder getOrder() {
        return order;
    }

    public void setOrder(InwardOrder order) {
        this.order = order;
    }
}
