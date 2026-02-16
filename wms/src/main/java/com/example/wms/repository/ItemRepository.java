package com.example.wms.repository;

import com.example.wms.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Item entity.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
