package com.example.commons.dto.inventory;

public class StageTransitionResponse {
    private Long id;
    private Long fromStageId;
    private Long toStageId;

    public StageTransitionResponse() {}

    public StageTransitionResponse(Long id, Long fromStageId, Long toStageId) {
        this.id = id;
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
