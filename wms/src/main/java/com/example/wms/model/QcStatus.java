package com.example.wms.model;

/**
 * QC (Quality Control) status for Box and Item in WMS.
 * Enforced match when adding Item to Box.
 */
public enum QcStatus {
    PASS,
    FAIL,
    UNKNOWN
}
