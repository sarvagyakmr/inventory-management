package com.example.inventory.repository;

import com.example.inventory.model.UomConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UomConversionRepository extends JpaRepository<UomConversion, Long> {
    Optional<UomConversion> findByFromSkuIdAndFromUnitOfMeasureAndToSkuIdAndToUnitOfMeasure(
            String fromSkuId, String fromUnitOfMeasure, String toSkuId, String toUnitOfMeasure);

    boolean existsByFromSkuIdAndFromUnitOfMeasureAndToSkuIdAndToUnitOfMeasure(
            String fromSkuId, String fromUnitOfMeasure, String toSkuId, String toUnitOfMeasure);
}