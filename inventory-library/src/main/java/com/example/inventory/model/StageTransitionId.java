package com.example.inventory.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StageTransitionId implements Serializable {

    private String fromStageId;
    private String toStageId;

    public StageTransitionId() {
    }

    public StageTransitionId(String fromStageId, String toStageId) {
        this.fromStageId = fromStageId;
        this.toStageId = toStageId;
    }

    public String getFromStageId() {
        return fromStageId;
    }

    public void setFromStageId(String fromStageId) {
        this.fromStageId = fromStageId;
    }

    public String getToStageId() {
        return toStageId;
    }

    public void setToStageId(String toStageId) {
        this.toStageId = toStageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageTransitionId that = (StageTransitionId) o;
        return Objects.equals(fromStageId, that.fromStageId) && Objects.equals(toStageId, that.toStageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromStageId, toStageId);
    }
}