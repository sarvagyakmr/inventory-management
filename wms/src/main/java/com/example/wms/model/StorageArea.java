package com.example.wms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.example.commons.enums.StorageAreaType;

/**
 * StorageArea entity for warehouse configuration.
 * id: long auto-increment
 * description: user input
 * type: StorageAreaType (provided at creation): INWARD, OUTWARD, STORAGE,
 * PIGEON_HOLE
 */
@Entity
@Table(name = "storage_area")
public class StorageArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private StorageAreaType type;

    public StorageArea() {
    }

    /**
     * Constructor with type for creation.
     */
    public StorageArea(String description, StorageAreaType type) {
        this.description = description;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StorageAreaType getType() {
        return type;
    }

    public void setType(StorageAreaType type) {
        this.type = type;
    }
}
