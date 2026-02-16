package com.example.inventory.config;

import com.example.inventory.repository.InventoryRepository;
import com.example.inventory.repository.ProcessedMessageRepository;
import com.example.inventory.repository.ProductRepository;
import com.example.inventory.repository.ProductUomRepository;
import com.example.inventory.repository.StageRepository;
import com.example.inventory.repository.StageTransitionRepository;
import com.example.inventory.repository.StorageLocationRepository;
import com.example.inventory.repository.UomConversionRepository;
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
    public InventoryService inventoryService(InventoryRepository inventoryRepository,
            StorageLocationRepository locationRepository, ProductRepository productRepository,
            ProductUomRepository productUomRepository, UomConversionRepository uomConversionRepository,
            ProcessedMessageRepository processedMessageRepository, StageRepository stageRepository,
            StageTransitionRepository stageTransitionRepository) {
        return new InventoryServiceImpl(inventoryRepository, locationRepository, productRepository,
                productUomRepository, uomConversionRepository, processedMessageRepository, stageRepository,
                stageTransitionRepository);
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
