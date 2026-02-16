package com.example.inventory.repository;

import com.example.commons.enums.UnitOfMeasure;
import com.example.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
        Optional<Inventory> findBySkuIdAndUnitOfMeasureAndLocationIdAndStageId(
                        String skuId, UnitOfMeasure unitOfMeasure, String locationId, Long stageId);

        boolean existsBySkuIdAndUnitOfMeasureAndLocationIdAndStageId(
                        String skuId, UnitOfMeasure unitOfMeasure, String locationId, Long stageId);
}
