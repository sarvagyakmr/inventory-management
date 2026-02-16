package com.example.wms;

import com.example.inventory.config.InventoryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Main Spring Boot application for WMS (Warehouse Management System).
 * Imports InventoryConfig to enable library entities, services (including WarehouseService), repositories.
 * Uses H2 in-memory DB for storage config.
 * Similar structure to example-usage and oms modules.
 */
@SpringBootApplication
@Import(InventoryConfig.class)
public class WmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsApplication.class, args);
    }
}