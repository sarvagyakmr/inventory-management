package com.example.inventory.repository;

import com.example.inventory.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Item entity.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}