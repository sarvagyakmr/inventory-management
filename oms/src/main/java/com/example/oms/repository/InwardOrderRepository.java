package com.example.oms.repository;

import com.example.oms.model.InwardOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InwardOrderRepository extends JpaRepository<InwardOrder, Long> {
}
