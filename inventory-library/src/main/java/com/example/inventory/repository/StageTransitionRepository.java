package com.example.inventory.repository;

import com.example.inventory.model.StageTransition;
import com.example.inventory.model.StageTransitionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageTransitionRepository extends JpaRepository<StageTransition, StageTransitionId> {
}