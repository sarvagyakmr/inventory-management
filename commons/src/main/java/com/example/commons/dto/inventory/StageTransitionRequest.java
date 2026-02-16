package com.example.commons.dto.inventory;

public class StageTransitionRequest {
    private Long fromStageId;
    private Long toStageId;

    public StageTransitionRequest() {}

    public StageTransitionRequest(Long fromStageId, Long toStageId) {
        this.fromStageId = fromStageId;
        this.toStageId = toStageId;
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
