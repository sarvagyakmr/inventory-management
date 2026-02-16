package com.example.inventory.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

/**
 * Box entity for warehouse storage.
 * - id: auto-increment Long
 * - type: StorageAreaType (INWARD, OUTWARD, STORAGE, PIGEON_HOLE) -- fixed at creation, must match location's storage area type
 * - status: dynamic StorageAreaType (updated to STORAGE on storage location assign for accuracy guardrails)
 * - qcStatus: QcStatus (PASS/FAIL/UNKNOWN; now input in REST)
 * - location: nullable reference to Location (for assignment; validated on update)
 * - items: Many-to-many via explicit join table 'box_item' (box_id, item_id) for mapping
 * - orderId: nullable Long; required for INWARD type (references OMS InwardOrder)
 */
@Entity
@Table(name = "box")
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StorageAreaType type;  // fixed at creation (e.g., INWARD)

    @Enumerated(EnumType.STRING)
    private StorageAreaType status;  // dynamic: updated to STORAGE on storage location assign (for accuracy)

    @Enumerated(EnumType.STRING)
    private QcStatus qcStatus;

    // Nullable reference to Location (id-based join for simplicity/consistency)
    @ManyToOne(optional = true)
    @JoinColumn(name = "location_id", nullable = true)
    private Location location;

    // Owning side of many-to-many with Item; creates box_item table (box_id, item_id)
    // @JsonManagedReference prevents circular serialization (Item -> Box loop)
    @ManyToMany
    @JsonManagedReference
    @JoinTable(
        name = "box_item",
        joinColumns = @JoinColumn(name = "box_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> items = new HashSet<>();

    // Nullable orderId (e.g., InwardOrder ID from OMS for INWARD boxes)
    private Long orderId;

    public Box() {
    }

    /**
     * Constructor for creation (type/qcStatus required; status=type initial; orderId/location/items nullable).
     */
    public Box(StorageAreaType type, QcStatus qcStatus) {
        this.type = type;
        this.status = type;  // initial status = type
        this.qcStatus = qcStatus != null ? qcStatus : QcStatus.UNKNOWN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StorageAreaType getType() {
        return type;
    }

    public void setType(StorageAreaType type) {
        this.type = type;
    }

    public StorageAreaType getStatus() {
        return status;
    }

    public void setStatus(StorageAreaType status) {
        this.status = status;
    }

    public QcStatus getQcStatus() {
        return qcStatus;
    }

    public void setQcStatus(QcStatus qcStatus) {
        this.qcStatus = qcStatus;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}