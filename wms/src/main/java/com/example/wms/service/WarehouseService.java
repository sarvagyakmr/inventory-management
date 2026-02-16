package com.example.wms.service;

import com.example.wms.model.Aisle;
import com.example.wms.model.Box;
import com.example.wms.model.Item;
import com.example.commons.enums.ItemStatus;
import com.example.wms.model.Location;
import com.example.commons.enums.LocationCapacityType;
import com.example.commons.enums.QcStatus;
import com.example.wms.model.StorageArea;
import com.example.commons.enums.StorageAreaType;

/**
 * Service for Warehouse Management System (WMS) storage location configuration.
 * Handles creation of StorageArea, Aisle, Location, Box, and Item (with
 * QC/type/ItemStatus validation).
 * ItemStatus state machine: CREATED (initial) -> INWARD (on INWARD Box) -> LIVE
 * (on STORAGE Location).
 */
public interface WarehouseService {

    /**
     * Create a new storage area. Type is required at creation.
     * 
     * @param description user provided description
     * @param type        StorageAreaType (INWARD, OUTWARD, STORAGE, PIGEON_HOLE)
     * @return created StorageArea with auto-generated ID
     */
    StorageArea createStorageArea(String description, StorageAreaType type);

    /**
     * Create a new aisle in the specified storage area.
     * ID format: S{storageAreaId}-A{aisleNumber} e.g., S1-A3 for the 3rd aisle.
     * 
     * @param storageAreaId ID of parent storage area
     * @return created Aisle
     */
    Aisle createAisle(Long storageAreaId);

    /**
     * Create a new location in the specified aisle (and area). Type is required.
     * ID format: {aisleId}-L{locationNumber} e.g., S1-A3-L4
     * 
     * @param storageAreaId ID of storage area
     * @param aisleId       ID of aisle
     * @param type          LocationCapacityType (SINGLE, MULTI)
     * @return created Location
     */
    Location createLocation(Long storageAreaId, String aisleId, LocationCapacityType type);

    /**
     * Create a new Box. ID auto-generated Long. Type and qcStatus required.
     * If type=INWARD, orderId required (validated via OMS in WMS layer).
     * Location nullable at creation.
     * 
     * @param type     StorageAreaType (must match any assigned location's area
     *                 type)
     * @param qcStatus QcStatus (PASS/FAIL/UNKNOWN; no default UNKNOWN)
     * @param orderId  nullable Long (InwardOrder ID from OMS for INWARD boxes)
     * @return created Box
     */
    Box createBox(StorageAreaType type, QcStatus qcStatus, Long orderId);

    /**
     * Update a Box's location (assign or clear). Enforces rule: Box can only be
     * added to
     * same type of location (i.e., Box.type == StorageArea.type via Location).
     * 
     * @param boxId      ID of Box
     * @param locationId ID of Location (nullable to unassign)
     * @return updated Box
     */
    Box updateBoxLocation(Long boxId, String locationId);

    /**
     * Create a new Item. ID auto-generated Long.
     * 
     * @param productId references Product.skuId
     * @param qcStatus  QcStatus (PASS/FAIL/UNKNOWN)
     * @return created Item
     */
    Item createItem(String productId, QcStatus qcStatus);

    /**
     * Add Item to Box (via box_item mapping table). Enforces QC match:
     * Item.qcStatus must == Box.qcStatus.
     * 
     * @param itemId ID of Item
     * @param boxId  ID of Box
     * @return updated Box
     */
    Box addItemToBox(Long itemId, Long boxId);
}
