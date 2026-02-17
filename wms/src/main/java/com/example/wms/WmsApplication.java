package com.example.wms;

import com.example.commons.client.oms.OmsClient;
import com.example.inventory.config.InventoryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main Spring Boot application for WMS (Warehouse Management System).
 * Imports InventoryConfig to enable inventory-library entities, services,
 * repositories.
 * Also scans local WMS entities, repositories, and services.
 * Uses H2 in-memory DB for storage config.
 *
 * Defines OmsClient bean explicitly here (in wms module) to ensure proper
 * initialization and Spring context integration for classes in dependency modules.
 */
@SpringBootApplication
@Import(InventoryConfig.class)
@EntityScan(basePackages = { "com.example.wms.model" })
@EnableJpaRepositories(basePackages = { "com.example.wms.repository" })
public class WmsApplication {

    /**
     * Defines OmsClient as a Spring bean in the wms module.
     * The constructor param resolves the base URL via @Value from properties.
     * This ensures the client is initialized correctly without relying on
     * cross-module component scanning.
     */
    @Bean
    public OmsClient omsClient(@Value("${oms.base-url:http://localhost:8081}/api/oms") String omsBaseUrl) {
        return new OmsClient(omsBaseUrl);
    }

    public static void main(String[] args) {
        SpringApplication.run(WmsApplication.class, args);
    }
}