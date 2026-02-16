package com.example.inventory.config;

import com.example.inventory.repository.AisleRepository;
import com.example.inventory.repository.BoxRepository;
import com.example.inventory.repository.InventoryRepository;
import com.example.inventory.repository.ItemRepository;
import com.example.inventory.repository.LocationRepository;
import com.example.inventory.repository.ProcessedMessageRepository;
import com.example.inventory.repository.ProductRepository;
import com.example.inventory.repository.ProductUomRepository;
import com.example.inventory.repository.StageRepository;
import com.example.inventory.repository.StageTransitionRepository;
import com.example.inventory.repository.StorageAreaRepository;
import com.example.inventory.repository.StorageLocationRepository;
import com.example.inventory.repository.UomConversionRepository;
import com.example.inventory.service.InventoryService;
import com.example.inventory.service.InventoryServiceImpl;
import com.example.inventory.service.WarehouseService;
import com.example.inventory.service.WarehouseServiceImpl;
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
    public InventoryService inventoryService(InventoryRepository inventoryRepository, StorageLocationRepository locationRepository, ProductRepository productRepository, ProductUomRepository productUomRepository, UomConversionRepository uomConversionRepository, ProcessedMessageRepository processedMessageRepository, StageRepository stageRepository, StageTransitionRepository stageTransitionRepository) {
        return new InventoryServiceImpl(inventoryRepository, locationRepository, productRepository, productUomRepository, uomConversionRepository, processedMessageRepository, stageRepository, stageTransitionRepository);
    }

    /**
     * Provide WarehouseService bean for WMS storage configuration.
     * This is core functionality in inventory-library used by WMS module.
     * Includes Box/Item support with QC/type/location validation and box_item mapping table.
     * Product validation delegated to OMS API (OMS as source of truth).
     */
    @Bean
    public WarehouseService warehouseService(StorageAreaRepository storageAreaRepository,
                                             AisleRepository aisleRepository,
                                             LocationRepository locationRepository,
                                             BoxRepository boxRepository,
                                             ItemRepository itemRepository) {
        return new WarehouseServiceImpl(storageAreaRepository, aisleRepository, locationRepository, boxRepository, itemRepository);
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
