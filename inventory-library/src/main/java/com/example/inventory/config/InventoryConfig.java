package com.example.inventory.config;

import com.example.inventory.repository.InventoryRepository;
import com.example.inventory.repository.StorageLocationRepository;
import com.example.inventory.service.InventoryService;
import com.example.inventory.service.InventoryServiceImpl;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.inventory.repository")
@EntityScan(basePackages = "com.example.inventory.model")
public class InventoryConfig {

    @Bean
    public InventoryService inventoryService(InventoryRepository inventoryRepository, StorageLocationRepository locationRepository) {
        return new InventoryServiceImpl(inventoryRepository, locationRepository);
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
