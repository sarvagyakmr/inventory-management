package com.example.wms.repository;

import com.example.wms.model.Location;
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
