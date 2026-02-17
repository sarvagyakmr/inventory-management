package com.example.oms;

import com.example.commons.client.masterdata.MasterDataClient;
import com.example.inventory.config.InventoryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main Spring Boot application for OMS.
 * Imports InventoryConfig for inventory-library.
 * Defines MasterDataClient bean explicitly to ensure initialization
 * for inter-service calls (resolves cross-module component scan issues).
 */
@SpringBootApplication
@Import(InventoryConfig.class)
@EntityScan(basePackages = "com.example.oms.model")
@EnableJpaRepositories(basePackages = "com.example.oms.repository")
public class OmsApplication {

    /**
     * Defines MasterDataClient as bean (base URL for master-data service APIs).
     * Used for supplier/customer verification in order creation.
     */
    @Bean
    public MasterDataClient masterDataClient(@Value("${masterdata.base-url:http://localhost:8083}/api/master-data") String masterDataBaseUrl) {
        return new MasterDataClient(masterDataBaseUrl);
    }

    public static void main(String[] args) {
        SpringApplication.run(OmsApplication.class, args);
    }
}
