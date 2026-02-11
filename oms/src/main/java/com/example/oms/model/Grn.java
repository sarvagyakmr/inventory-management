package com.example.oms.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grn")
public class Grn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private InwardOrder order;
    private LocalDateTime receivedDate;
    @OneToMany(mappedBy = "grn", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<GrnItem> items = new ArrayList<>();

    public Grn() {
    }

    public Grn(InwardOrder order, List<GrnItem> items) {
        this.order = order;
        this.receivedDate = LocalDateTime.now();
        this.items = items != null ? items : new ArrayList<>();
        for (GrnItem item : this.items) {
            item.setGrn(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InwardOrder getOrder() {
        return order;
    }

    public void setOrder(InwardOrder order) {
        this.order = order;
    }

    public LocalDateTime getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDateTime receivedDate) {
        this.receivedDate = receivedDate;
    }

    public List<GrnItem> getItems() {
        return items;
    }

    public void setItems(List<GrnItem> items) {
        this.items = items;
    }
}
