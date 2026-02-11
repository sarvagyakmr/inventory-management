package com.example.oms.repository;

import com.example.oms.model.Grn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrnRepository extends JpaRepository<Grn, Long> {
}
