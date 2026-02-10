package com.example.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "processed_message")
public class ProcessedMessage {

    @Id
    private String messageId;
    private LocalDateTime processedAt;

    public ProcessedMessage() {
    }

    public ProcessedMessage(String messageId) {
        this.messageId = messageId;
        this.processedAt = LocalDateTime.now();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }
}