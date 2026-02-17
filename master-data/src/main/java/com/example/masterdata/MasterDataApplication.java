package com.example.masterdata;

import com.example.inventory.config.InventoryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main Spring Boot application for Master Data service.
 * Manages shared master data like Customer and Supplier.
 * Imports InventoryConfig for inventory-library integration.
 * Uses H2 in-memory DB.
 * Runs on port 8083 by default to avoid conflicts with OMS/WMS.
 */
@SpringBootApplication
@Import(InventoryConfig.class)
@EntityScan(basePackages = { "com.example.masterdata.model" })
@EnableJpaRepositories(basePackages = { "com.example.masterdata.repository" })
public class MasterDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterDataApplication.class, args);
    }
}