package com.example.masterdata.repository;

import com.example.masterdata.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Supplier repository, moved to master-data module.
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}