package com.example.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Aisle entity in warehouse.
 * id: generated as S{storageAreaId}-A{aisleNumber} e.g. S1-A3
 * Input: storageAreaId
 */
@Entity
@Table(name = "aisle")
public class Aisle {

    @Id
    private String id;

    private Long storageAreaId;

    public Aisle() {
    }

    public Aisle(String id, Long storageAreaId) {
        this.id = id;
        this.storageAreaId = storageAreaId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getStorageAreaId() {
        return storageAreaId;
    }

    public void setStorageAreaId(Long storageAreaId) {
        this.storageAreaId = storageAreaId;
    }
}