package com.example.inventory.repository;

import com.example.inventory.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Location entity.
 * Provides countByAisleId for id generation.
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, String> {

    /**
     * Count locations in a given aisle to determine next location number.
     */
    long countByAisleId(String aisleId);
}