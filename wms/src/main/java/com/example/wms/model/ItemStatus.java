package com.example.wms.model;

/**
 * Status of Item in WMS for inventory accuracy/guardrails.
 * Transitions:
 * - CREATED (initial on create)
 * - INWARD (when added to INWARD Box)
 * - LIVE (when Box moved to STORAGE Location)
 * - PICKED, PACKED, SHIPPED (future states)
 */
public enum ItemStatus {
    CREATED,
    INWARD,
    LIVE,
    PICKED,
    PACKED,
    SHIPPED
}
