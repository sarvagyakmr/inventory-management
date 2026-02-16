package com.example.inventory.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

/**
 * Item entity for WMS (ties to product inventory).
 * - id: auto-increment Long
 * - productId: references Product.skuId
 * - qcStatus: QcStatus (PASS/FAIL/UNKNOWN) -- must match Box.qcStatus on assignment
 * - itemStatus: ItemStatus (CREATED default; transitions on Box/Location events for accuracy)
 * - boxes: Many-to-many via box_item join table (item can be in multiple boxes? but typically one)
 */
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;  // FK ref to Product

    @Enumerated(EnumType.STRING)
    private QcStatus qcStatus;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus = ItemStatus.CREATED;  // default

    // Many-to-many to Box; join table box_item (box_id, item_id) as specified
    // @JsonBackReference prevents circular serialization (Box -> Item -> Box loop)
    @ManyToMany(mappedBy = "items")
    @JsonBackReference
    private Set<Box> boxes = new HashSet<>();

    public Item() {
    }

    public Item(String productId, QcStatus qcStatus) {
        this.productId = productId;
        this.qcStatus = qcStatus;
        // itemStatus defaults to CREATED
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public QcStatus getQcStatus() {
        return qcStatus;
    }

    public void setQcStatus(QcStatus qcStatus) {
        this.qcStatus = qcStatus;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(Set<Box> boxes) {
        this.boxes = boxes;
    }
}