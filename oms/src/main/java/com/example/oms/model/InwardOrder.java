package com.example.oms.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.example.commons.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inward_order")
public class InwardOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String locationId;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<InwardOrderItem> items = new ArrayList<>();

    public InwardOrder() {
    }

    public InwardOrder(Supplier supplier, String locationId, List<InwardOrderItem> items) {
        this.supplier = supplier;
        this.locationId = locationId;
        this.status = OrderStatus.CREATED;
        this.items = items != null ? items : new ArrayList<>();
        for (InwardOrderItem item : this.items) {
            item.setOrder(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<InwardOrderItem> getItems() {
        return items;
    }

    public void setItems(List<InwardOrderItem> items) {
        this.items = items;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
