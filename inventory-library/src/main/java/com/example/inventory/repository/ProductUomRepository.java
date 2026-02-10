package com.example.inventory.repository;

import com.example.inventory.model.ProductId;
import com.example.inventory.model.ProductUom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductUomRepository extends JpaRepository<ProductUom, ProductId> {
}