package com.example.inventory.repository;

import com.example.inventory.model.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Box entity.
 */
@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {
}