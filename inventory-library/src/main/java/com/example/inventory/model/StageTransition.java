package com.example.inventory.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "stage_transition")
public class StageTransition {

    @EmbeddedId
    private StageTransitionId id;

    public StageTransition() {
    }

    public StageTransition(String fromStageId, String toStageId) {
        this.id = new StageTransitionId(fromStageId, toStageId);
    }

    public StageTransitionId getId() {
        return id;
    }

    public void setId(StageTransitionId id) {
        this.id = id;
    }
}