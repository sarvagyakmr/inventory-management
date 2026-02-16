package com.example.inventory.service;

import com.example.inventory.model.Inventory;
import com.example.inventory.model.LocationType;
import com.example.inventory.model.ProcessedMessage;
import com.example.inventory.model.Product;
import com.example.inventory.model.ProductUom;
import com.example.inventory.model.Stage;
import com.example.inventory.model.StageTransition;
import com.example.inventory.model.StorageLocation;
import com.example.inventory.model.UomConversion;
import com.example.inventory.repository.InventoryRepository;
import com.example.inventory.repository.ProcessedMessageRepository;
import com.example.inventory.repository.ProductRepository;
import com.example.inventory.repository.ProductUomRepository;
import com.example.inventory.repository.StageRepository;
import com.example.inventory.repository.StageTransitionRepository;
import com.example.inventory.repository.StorageLocationRepository;
import com.example.inventory.repository.UomConversionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final StorageLocationRepository locationRepository;
    private final ProductRepository productRepository;
    private final ProductUomRepository productUomRepository;
    private final UomConversionRepository uomConversionRepository;
    private final ProcessedMessageRepository processedMessageRepository;
    private final StageRepository stageRepository;
    private final StageTransitionRepository stageTransitionRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, StorageLocationRepository locationRepository,
            ProductRepository productRepository, ProductUomRepository productUomRepository,
            UomConversionRepository uomConversionRepository, ProcessedMessageRepository processedMessageRepository,
            StageRepository stageRepository, StageTransitionRepository stageTransitionRepository) {
        this.inventoryRepository = inventoryRepository;
        this.locationRepository = locationRepository;
        this.productRepository = productRepository;
        this.productUomRepository = productUomRepository;
        this.uomConversionRepository = uomConversionRepository;
        this.processedMessageRepository = processedMessageRepository;
        this.stageRepository = stageRepository;
        this.stageTransitionRepository = stageTransitionRepository;
    }

    @Override
    @Transactional
    public StorageLocation addStorageLocation(LocationType type, String description) {
        String locationId = UUID.randomUUID().toString();
        if (locationRepository.existsById(locationId)) {
            throw new IllegalArgumentException("Location already exists");
        }
        StorageLocation location = new StorageLocation(locationId, type, description);
        return locationRepository.save(location);
    }

    @Override
    @Transactional
    public Stage addStage(String name, String description) {
        Stage stage = new Stage(name, description);
        return stageRepository.save(stage);
    }

    @Override
    @Transactional
    public StageTransition addStageTransition(Long fromStageId, Long toStageId) {
        if (!stageRepository.existsById(fromStageId)) {
            throw new IllegalArgumentException("From stage not found");
        }
        if (!stageRepository.existsById(toStageId)) {
            throw new IllegalArgumentException("To stage not found");
        }
        if (stageTransitionRepository.existsByFromStageIdAndToStageId(fromStageId, toStageId)) {
            throw new IllegalArgumentException("Transition already defined");
        }
        StageTransition transition = new StageTransition(fromStageId, toStageId);
        return stageTransitionRepository.save(transition);
    }

    @Override
    @Transactional
    public Product addProduct(String skuId) {
        if (productRepository.existsById(skuId)) {
            throw new IllegalArgumentException("Product already exists");
        }
        Product product = new Product(skuId);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public ProductUom addUom(String skuId, String unitOfMeasure) {
        if (!productRepository.existsById(skuId)) {
            throw new IllegalArgumentException("Product not found");
        }
        if (productUomRepository.existsBySkuIdAndUnitOfMeasure(skuId, unitOfMeasure)) {
            throw new IllegalArgumentException("UOM already defined for product");
        }
        ProductUom productUom = new ProductUom(skuId, unitOfMeasure);
        return productUomRepository.save(productUom);
    }

    @Override
    @Transactional
    public UomConversion addConversion(String skuId, String fromUnitOfMeasure, String toUnitOfMeasure, double factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("Conversion factor must be positive");
        }
        if (!productUomRepository.existsBySkuIdAndUnitOfMeasure(skuId, fromUnitOfMeasure)) {
            throw new IllegalArgumentException("From UOM not defined");
        }
        if (!productUomRepository.existsBySkuIdAndUnitOfMeasure(skuId, toUnitOfMeasure)) {
            throw new IllegalArgumentException("To UOM not defined");
        }
        if (uomConversionRepository.existsByFromSkuIdAndFromUnitOfMeasureAndToSkuIdAndToUnitOfMeasure(skuId,
                fromUnitOfMeasure, skuId, toUnitOfMeasure)) {
            throw new IllegalArgumentException("Conversion already defined");
        }
        UomConversion conversion = new UomConversion(skuId, fromUnitOfMeasure, skuId, toUnitOfMeasure, factor);
        return uomConversionRepository.save(conversion);
    }

    @Override
    @Transactional
    public Inventory addInventory(String skuId, String unitOfMeasure, String locationId, Long stageId) {
        if (!locationRepository.existsById(locationId)) {
            throw new IllegalArgumentException("Location not found");
        }
        if (!stageRepository.existsById(stageId)) {
            throw new IllegalArgumentException("Stage not found");
        }
        if (!productRepository.existsById(skuId)) {
            throw new IllegalArgumentException("Product not found");
        }
        if (!productUomRepository.existsBySkuIdAndUnitOfMeasure(skuId, unitOfMeasure)) {
            throw new IllegalArgumentException("UOM not defined for product");
        }
        if (inventoryRepository.existsBySkuIdAndUnitOfMeasureAndLocationIdAndStageId(skuId, unitOfMeasure, locationId,
                stageId)) {
            throw new IllegalArgumentException("Inventory already exists at location");
        }
        Inventory inventory = new Inventory(skuId, unitOfMeasure, locationId, stageId, 0);
        Product product = productRepository.findById(skuId).get();
        Stage stage = stageRepository.findById(stageId).get();
        inventory.setProduct(product);
        inventory.setStage(stage);
        return inventoryRepository.save(inventory);
    }

    private void checkAndRecordMessageId(String messageId) {
        if (processedMessageRepository.existsById(messageId)) {
            throw new IllegalArgumentException("Message already processed (idempotent)");
        }
        processedMessageRepository.save(new ProcessedMessage(messageId));
    }

    @Override
    @Transactional
    public Inventory adjustQuantity(String skuId, String unitOfMeasure, String locationId, Long stageId,
            int quantityChange, String messageId) {
        checkAndRecordMessageId(messageId);
        Inventory inventory = inventoryRepository
                .findBySkuIdAndUnitOfMeasureAndLocationIdAndStageId(skuId, unitOfMeasure, locationId, stageId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found at location"));
        int newQuantity = inventory.getQuantity() + quantityChange;
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        inventory.setQuantity(newQuantity);
        return inventoryRepository.save(inventory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Inventory> getInventory(String skuId, String unitOfMeasure) {
        return inventoryRepository.findAll().stream()
                .filter(inv -> skuId.equals(inv.getSkuId()) && unitOfMeasure.equals(inv.getUnitOfMeasure()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StorageLocation> getLocationsByType(LocationType type) {
        return locationRepository.findAll().stream()
                .filter(loc -> loc.getType() == type)
                .toList();
    }

    @Override
    @Transactional
    public Inventory moveInventory(String skuId, String unitOfMeasure, String fromLocationId, String toLocationId,
            Long stageId, int quantityToMove) {
        if (quantityToMove <= 0) {
            throw new IllegalArgumentException("Quantity to move must be positive");
        }
        if (!locationRepository.existsById(fromLocationId) || !locationRepository.existsById(toLocationId)) {
            throw new IllegalArgumentException("Location not found");
        }
        if (!productUomRepository.existsBySkuIdAndUnitOfMeasure(skuId, unitOfMeasure)) {
            throw new IllegalArgumentException("UOM not defined for product");
        }
        Inventory fromInv = inventoryRepository
                .findBySkuIdAndUnitOfMeasureAndLocationIdAndStageId(skuId, unitOfMeasure, fromLocationId, stageId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found at from location"));
        if (fromInv.getQuantity() < quantityToMove) {
            throw new IllegalArgumentException("Insufficient quantity at from location");
        }
        fromInv.setQuantity(fromInv.getQuantity() - quantityToMove);
        inventoryRepository.save(fromInv);
        Inventory toInv = inventoryRepository
                .findBySkuIdAndUnitOfMeasureAndLocationIdAndStageId(skuId, unitOfMeasure, toLocationId, stageId)
                .orElseGet(() -> {
                    Inventory inv = new Inventory(skuId, unitOfMeasure, toLocationId, stageId, 0);
                    Product product = productRepository.findById(skuId).get();
                    Stage stage = stageRepository.findById(stageId).get();
                    inv.setProduct(product);
                    inv.setStage(stage);
                    return inv;
                });
        toInv.setQuantity(toInv.getQuantity() + quantityToMove);
        return inventoryRepository.save(toInv);
    }

    @Override
    @Transactional
    public Inventory convertUom(String skuId, String fromUnitOfMeasure, String toUnitOfMeasure, Long stageId,
            int quantityToConvert, String locationId, String messageId) {
        checkAndRecordMessageId(messageId);
        if (quantityToConvert <= 0) {
            throw new IllegalArgumentException("Quantity to convert must be positive");
        }
        if (!locationRepository.existsById(locationId)) {
            throw new IllegalArgumentException("Location not found");
        }
        UomConversion conversion = uomConversionRepository
                .findByFromSkuIdAndFromUnitOfMeasureAndToSkuIdAndToUnitOfMeasure(skuId, fromUnitOfMeasure, skuId,
                        toUnitOfMeasure)
                .orElseThrow(() -> new IllegalArgumentException("Conversion not defined"));
        double factor = conversion.getFactor();
        // from inventory
        Inventory fromInv = inventoryRepository
                .findBySkuIdAndUnitOfMeasureAndLocationIdAndStageId(skuId, fromUnitOfMeasure, locationId, stageId)
                .orElseThrow(() -> new IllegalArgumentException("From UOM inventory not found at location"));
        if (fromInv.getQuantity() < quantityToConvert) {
            throw new IllegalArgumentException("Insufficient quantity in from UOM");
        }
        // deduct
        fromInv.setQuantity(fromInv.getQuantity() - quantityToConvert);
        inventoryRepository.save(fromInv);
        // to inventory (convert qty, create if not)
        int toQuantity = (int) (quantityToConvert * factor);
        Inventory toInv = inventoryRepository
                .findBySkuIdAndUnitOfMeasureAndLocationIdAndStageId(skuId, toUnitOfMeasure, locationId, stageId)
                .orElseGet(() -> {
                    Inventory inv = new Inventory(skuId, toUnitOfMeasure, locationId, stageId, 0); // same stage
                    Product product = productRepository.findById(skuId).get();
                    Stage stage = stageRepository.findById(stageId).get();
                    inv.setProduct(product);
                    inv.setStage(stage);
                    return inv;
                });
        toInv.setQuantity(toInv.getQuantity() + toQuantity);
        return inventoryRepository.save(toInv);
    }

    @Override
    @Transactional
    public Inventory moveToStage(String skuId, String unitOfMeasure, String locationId, Long fromStageId,
            Long toStageId, int quantityToMove, String messageId) {
        checkAndRecordMessageId(messageId);
        if (quantityToMove <= 0) {
            throw new IllegalArgumentException("Quantity to move must be positive");
        }
        if (!stageTransitionRepository.existsByFromStageIdAndToStageId(fromStageId, toStageId)) {
            throw new IllegalArgumentException("Stage transition not allowed");
        }
        // from inventory
        Inventory fromInv = inventoryRepository
                .findBySkuIdAndUnitOfMeasureAndLocationIdAndStageId(skuId, unitOfMeasure, locationId, fromStageId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found at from stage"));
        if (fromInv.getQuantity() < quantityToMove) {
            throw new IllegalArgumentException("Insufficient quantity at from stage");
        }
        // deduct
        fromInv.setQuantity(fromInv.getQuantity() - quantityToMove);
        inventoryRepository.save(fromInv);
        // to inventory (create if not)
        Inventory toInv = inventoryRepository
                .findBySkuIdAndUnitOfMeasureAndLocationIdAndStageId(skuId, unitOfMeasure, locationId, toStageId)
                .orElseGet(() -> {
                    Inventory inv = new Inventory(skuId, unitOfMeasure, locationId, toStageId, 0);
                    Product product = productRepository.findById(skuId).get();
                    Stage toStage = stageRepository.findById(toStageId).get();
                    inv.setProduct(product);
                    inv.setStage(toStage);
                    return inv;
                });
        toInv.setQuantity(toInv.getQuantity() + quantityToMove);
        return inventoryRepository.save(toInv);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProduct(String skuId) {
        // OMS is source of truth; this supports it via library core
        return productRepository.findById(skuId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + skuId));
    }
}
