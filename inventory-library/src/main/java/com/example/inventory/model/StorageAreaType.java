package com.example.inventory.model;

/**
 * Types for StorageArea in warehouse configuration.
 * Provided at creation time.
 */
public enum StorageAreaType {
    INWARD,    // For inward/receiving areas
    OUTWARD,   // For outward/shipping areas
    STORAGE,   // General storage
    PIGEON_HOLE // Specialized pigeon-hole storage
}