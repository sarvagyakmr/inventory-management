package com.example.oms.service;

import com.example.commons.enums.UnitOfMeasure;
import com.example.inventory.model.Inventory;
import com.example.commons.enums.LocationType;
import com.example.inventory.model.Product;
import com.example.inventory.model.ProductUom;
import com.example.inventory.model.Stage;
import com.example.inventory.model.StorageLocation;
import com.example.inventory.service.InventoryService;
import com.example.oms.model.Customer;
import com.example.commons.enums.FulfillableStatus;
import com.example.oms.model.Grn;
import com.example.oms.model.GrnItem;
import com.example.oms.model.InwardOrder;
import com.example.oms.model.InwardOrderItem;
import com.example.commons.enums.InventoryMovement;
import com.example.commons.enums.OrderStatus;
import com.example.oms.model.PurchaseOrder;
import com.example.oms.model.PurchaseOrderItem;
import com.example.oms.model.Supplier;
import com.example.oms.repository.CustomerRepository;
import com.example.oms.repository.GrnRepository;
import com.example.oms.repository.InwardOrderRepository;
import com.example.oms.repository.PurchaseOrderRepository;
import com.example.oms.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OmsService {

    private final InventoryService inventoryService;
    private final SupplierRepository supplierRepository;
    private final InwardOrderRepository inwardOrderRepository;
    private final GrnRepository grnRepository;
    private final CustomerRepository customerRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    public OmsService(InventoryService inventoryService, SupplierRepository supplierRepository,
            InwardOrderRepository inwardOrderRepository, GrnRepository grnRepository,
            CustomerRepository customerRepository, PurchaseOrderRepository purchaseOrderRepository) {
        this.inventoryService = inventoryService;
        this.supplierRepository = supplierRepository;
        this.inwardOrderRepository = inwardOrderRepository;
        this.grnRepository = grnRepository;
        this.customerRepository = customerRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public StorageLocation createLocation(LocationType type, String description) {
        StorageLocation location = inventoryService.addStorageLocation(type, description);

        List<Stage> stages = new ArrayList<>();
        for (InventoryMovement movement : InventoryMovement.values()) {
            Stage stage = inventoryService.addStage(movement.name(), movement.name() + " stage");
            stages.add(stage);
        }

        if (stages.size() >= 4) {
            inventoryService.addStageTransition(stages.get(0).getId(), stages.get(1).getId());
            inventoryService.addStageTransition(stages.get(1).getId(), stages.get(2).getId());
            inventoryService.addStageTransition(stages.get(2).getId(), stages.get(3).getId());
        }

        return location;
    }

    public Product createProduct(String skuId) {
        return inventoryService.addProduct(skuId);
    }

    public Inventory updateInventory(String skuId, UnitOfMeasure unitOfMeasure, String locationId, Long stageId,
            int quantityChange, String messageId) {
        return inventoryService.adjustQuantity(skuId, unitOfMeasure, locationId, stageId, quantityChange, messageId);
    }

    public Inventory makeInventoryTransition(String skuId, UnitOfMeasure unitOfMeasure, String locationId,
            Long fromStageId,
            Long toStageId, int quantityToMove, String messageId) {
        return inventoryService.moveToStage(skuId, unitOfMeasure, locationId, fromStageId, toStageId, quantityToMove,
                messageId);
    }

    public Inventory convertProductUom(String skuId, UnitOfMeasure fromUnitOfMeasure, UnitOfMeasure toUnitOfMeasure,
            Long stageId,
            int quantityToConvert, String locationId, String messageId) {
        return inventoryService.convertUom(skuId, fromUnitOfMeasure, toUnitOfMeasure, stageId, quantityToConvert,
                locationId, messageId);
    }

    public Supplier addSupplier(String name, String contactInfo) {
        Supplier supplier = new Supplier(name, contactInfo);
        return supplierRepository.save(supplier);
    }

    public InwardOrder createInwardOrder(Long supplierId, String locationId, List<InwardOrderItem> items) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
        // order now linked to location (per req; GRN will use it)
        InwardOrder order = new InwardOrder(supplier, locationId, items);
        return inwardOrderRepository.save(order);
    }

    public InwardOrder updateOrderStatus(Long orderId, OrderStatus newStatus) {
        InwardOrder order = inwardOrderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(newStatus);
        return inwardOrderRepository.save(order);
    }

    public ProductUom addProductUom(String skuId, UnitOfMeasure unitOfMeasure) {
        // also ensure product exists in IMS
        try {
            inventoryService.addProduct(skuId);
        } catch (IllegalArgumentException e) {
            // ignore if exists
        }
        return inventoryService.addUom(skuId, unitOfMeasure);
    }

    public Grn createGrn(Long orderId, List<GrnItem> items) {
        InwardOrder order = inwardOrderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        if (order.getStatus() != OrderStatus.PROCESSING) {
            throw new IllegalArgumentException("Order must be in PROCESSING state to create GRN");
        }
        if (order.getLocationId() == null) {
            throw new IllegalArgumentException("Order must be linked to a location");
        }

        // create GRN for inward (linked to order which has location)
        Grn grn = new Grn(order, items);
        grn = grnRepository.save(grn);

        // ensure RECEIVED stage in IMS (create if needed; dupe names ok for demo)
        Stage receivedStage = inventoryService.addStage(InventoryMovement.RECEIVED.name(), "Received goods stage");

        // for each GRN item, inward to IMS: create product/uom if needed, add inventory
        // to RECEIVED stage
        // use order's location (moved from GRN step per req)
        for (GrnItem item : items) {
            try {
                inventoryService.addProduct(item.getSkuId());
            } catch (IllegalArgumentException e) {
                // ignore if exists
            }
            try {
                inventoryService.addUom(item.getSkuId(), item.getUnitOfMeasure());
            } catch (IllegalArgumentException e) {
                // ignore if exists
            }
            // add inventory (qty as received) to location in RECEIVED stage; auto in
            // received per req
            inventoryService.addInventory(item.getSkuId(), item.getUnitOfMeasure(), order.getLocationId(),
                    receivedStage.getId());
        }

        return grn;
    }

    public Customer addCustomer(String name, String contactInfo) {
        Customer customer = new Customer(name, contactInfo);
        return customerRepository.save(customer);
    }

    @Transactional
    public PurchaseOrder createPurchaseOrder(Long customerId, String locationId, List<PurchaseOrderItem> items) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        // determine fulfillable based on inventory in RECEIVED or AVAILABLE stages
        FulfillableStatus status = determineFulfillableStatus(items);
        PurchaseOrder order = new PurchaseOrder(customer, locationId, items, status);
        order = purchaseOrderRepository.save(order);
        // if fulfillable, allocate inventory by moving to ALLOCATED stage
        if (status == FulfillableStatus.FULFILLABLE) {
            allocateInventoryForOrder(items, locationId);
        }
        return order;
    }

    @Transactional(readOnly = true)
    private FulfillableStatus determineFulfillableStatus(List<PurchaseOrderItem> items) {
        if (items == null || items.isEmpty()) {
            return FulfillableStatus.UNFULFILLABLE;
        }
        // good stages for fulfillable (check by name via assoc)
        java.util.Set<String> goodStageNames = java.util.Set.of(
                InventoryMovement.RECEIVED.name(), InventoryMovement.AVAILABLE.name());
        for (PurchaseOrderItem item : items) {
            List<Inventory> inventories = inventoryService.getInventory(item.getSkuId(), item.getUnitOfMeasure());
            // check for sufficient qty in good stage (existence alone not enough to avoid
            // allocate failure)
            boolean hasGoodInventory = inventories.stream()
                    .anyMatch(inv -> inv.getStage() != null
                            && goodStageNames.contains(inv.getStage().getName())
                            && inv.getQuantity() >= item.getQuantity());
            if (!hasGoodInventory) {
                return FulfillableStatus.UNFULFILLABLE;
            }
        }
        return FulfillableStatus.FULFILLABLE;
    }

    @Transactional
    private void allocateInventoryForOrder(List<PurchaseOrderItem> items, String locationId) {
        // ensure ALLOCATED stage
        Stage allocatedStage = inventoryService.addStage(InventoryMovement.ALLOCATED.name(), "Allocated stage");
        Long toStageId = allocatedStage.getId();
        // for each item, find inventory in good stage and move qty to ALLOCATED (using
        // moveToStage for transition)
        java.util.Set<String> goodStageNames = java.util.Set.of(
                InventoryMovement.RECEIVED.name(), InventoryMovement.AVAILABLE.name());
        for (PurchaseOrderItem item : items) {
            List<Inventory> inventories = inventoryService.getInventory(item.getSkuId(), item.getUnitOfMeasure());
            // pick first good stage inv with sufficient qty (simple; could split if multi)
            Inventory fromInv = inventories.stream()
                    .filter(inv -> inv.getStage() != null && goodStageNames.contains(inv.getStage().getName())
                            && inv.getQuantity() >= item.getQuantity())
                    .findFirst()
                    .orElseThrow(
                            () -> new IllegalArgumentException("Insufficient inventory in good stage for allocation"));
            Long fromStageId = fromInv.getStageId();
            String messageId = java.util.UUID.randomUUID().toString();
            // move to allocated (fulfills allocation)
            inventoryService.moveToStage(item.getSkuId(), item.getUnitOfMeasure(), locationId,
                    fromStageId, toStageId, item.getQuantity(), messageId);
        }
    }

    public PurchaseOrder getPurchaseOrderById(Long orderId) {
        return purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    /**
     * Get Product by ID (skuId). OMS acts as source of truth for products,
     * wrapping core inventory library. Used by WMS for validation.
     */
    public Product getProduct(String skuId) {
        return inventoryService.getProduct(skuId);
    }

    /**
     * Get InwardOrder by ID. Used by WMS for Box creation validation when
     * type=INWARD.
     * OMS is source of truth for orders.
     */
    public InwardOrder getInwardOrder(Long orderId) {
        return inwardOrderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Inward order not found: " + orderId));
    }
}
