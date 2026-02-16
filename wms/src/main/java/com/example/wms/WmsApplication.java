package com.example.wms;

import com.example.inventory.config.InventoryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main Spring Boot application for WMS (Warehouse Management System).
 * Imports InventoryConfig to enable inventory-library entities, services,
 * repositories.
 * Also scans local WMS entities, repositories, and services.
 * Uses H2 in-memory DB for storage config.
 */
@SpringBootApplication
@Import(InventoryConfig.class)
@EntityScan(basePackages = { "com.example.wms.model", "com.example.inventory.model" })
@EnableJpaRepositories(basePackages = { "com.example.wms.repository", "com.example.inventory.repository" })
public class WmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsApplication.class, args);
    }
}