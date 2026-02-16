package com.example.inventory.repository;

import com.example.inventory.model.Aisle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Aisle entity.
 * Provides countByStorageAreaId for id generation.
 */
@Repository
public interface AisleRepository extends JpaRepository<Aisle, String> {

    /**
     * Count aisles in a given storage area to determine next aisle number.
     */
    long countByStorageAreaId(Long storageAreaId);
}