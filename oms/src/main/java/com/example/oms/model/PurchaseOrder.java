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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.example.commons.enums.FulfillableStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // customerId only (Long) to remove direct entity dependency on master-data module.
    // @JoinColumn preserves FK relationship in DB; no @ManyToOne object ref.
    @JoinColumn(name = "customer_id")
    private Long customerId;
    private String locationId;
    @Enumerated(EnumType.STRING)
    private FulfillableStatus fulfillableStatus;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PurchaseOrderItem> items = new ArrayList<>();

    public PurchaseOrder() {
    }

    /**
     * Constructor using customerId (Long) only - no direct Customer entity reference.
     * Updated to decouple OMS from master-data entities.
     */
    public PurchaseOrder(Long customerId, String locationId, List<PurchaseOrderItem> items,
            FulfillableStatus fulfillableStatus) {
        this.customerId = customerId;
        this.locationId = locationId;
        this.fulfillableStatus = fulfillableStatus;
        this.items = items != null ? items : new ArrayList<>();
        for (PurchaseOrderItem item : this.items) {
            item.setOrder(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets customerId (Long only; no entity ref).
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets customerId (Long only; no entity ref).
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public FulfillableStatus getFulfillableStatus() {
        return fulfillableStatus;
    }

    public void setFulfillableStatus(FulfillableStatus fulfillableStatus) {
        this.fulfillableStatus = fulfillableStatus;
    }

    public List<PurchaseOrderItem> getItems() {
        return items;
    }

    public void setItems(List<PurchaseOrderItem> items) {
        this.items = items;
    }
}
