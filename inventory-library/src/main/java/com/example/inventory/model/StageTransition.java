package com.example.inventory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stage_transition")
public class StageTransition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_stage_id")
    private Long fromStageId;

    @Column(name = "to_stage_id")
    private Long toStageId;

    public StageTransition() {
    }

    public StageTransition(Long fromStageId, Long toStageId) {
        this.fromStageId = fromStageId;
        this.toStageId = toStageId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromStageId() {
        return fromStageId;
    }

    public void setFromStageId(Long fromStageId) {
        this.fromStageId = fromStageId;
    }

    public Long getToStageId() {
        return toStageId;
    }

    public void setToStageId(Long toStageId) {
        this.toStageId = toStageId;
    }
}