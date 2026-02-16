package com.example.wms.repository;

import com.example.wms.model.StorageArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for StorageArea entity.
 */
@Repository
public interface StorageAreaRepository extends JpaRepository<StorageArea, Long> {
}
