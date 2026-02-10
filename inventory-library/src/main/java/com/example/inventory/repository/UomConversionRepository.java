package com.example.inventory.repository;

import com.example.inventory.model.UomConversion;
import com.example.inventory.model.UomConversionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UomConversionRepository extends JpaRepository<UomConversion, UomConversionId> {
}