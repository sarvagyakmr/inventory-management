package com.example.masterdata.repository;

import com.example.masterdata.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Customer repository, moved to master-data module.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}