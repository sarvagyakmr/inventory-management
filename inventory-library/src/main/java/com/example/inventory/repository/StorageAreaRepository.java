package com.example.inventory.repository;

import com.example.inventory.model.StorageArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for StorageArea entity.
 */
@Repository
public interface StorageAreaRepository extends JpaRepository<StorageArea, Long> {
}