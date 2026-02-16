package com.example.wms.repository;

import com.example.wms.model.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Box entity.
 */
@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {
}
