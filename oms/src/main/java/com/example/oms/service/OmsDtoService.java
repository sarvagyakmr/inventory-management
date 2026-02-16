package com.example.oms.service;

import com.example.commons.dto.inventory.*;
import com.example.commons.dto.oms.*;
import com.example.commons.enums.LocationType;
import com.example.inventory.model.*;
import com.example.oms.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OmsDtoService {

    private final OmsService omsService;

    public OmsDtoService(OmsService omsService) {
        this.omsService = omsService;
    }

    public StorageLocationResponse createLocation(StorageLocationRequest request) {
        StorageLocation location = omsService.createLocation(request.getType(), request.getDescription());
        return mapToStorageLocationResponse(location);
    }

    public ProductResponse createProduct(ProductRequest request) {
        Product product = omsService.createProduct(request.getSkuId());
        return mapToProductResponse(product);
    }

    public InventoryResponse updateInventory(AdjustInventoryRequest request) {
        Inventory inventory = omsService.updateInventory(request.getSkuId(), request.getUnitOfMeasure(),
                request.getLocationId(), request.getStageId(), request.getQuantityChange(), request.getMessageId());
        return mapToInventoryResponse(inventory);
    }

    public InventoryResponse makeInventoryTransition(TransitionInventoryRequest request) {
        Inventory inventory = omsService.makeInventoryTransition(request.getSkuId(), request.getUnitOfMeasure(),
                request.getLocationId(), request.getFromStageId(), request.getToStageId(), request.getQuantityToMove(),
                request.getMessageId());
        return mapToInventoryResponse(inventory);
    }

    public InventoryResponse convertProductUom(ConvertInventoryUomRequest request) {
        Inventory inventory = omsService.convertProductUom(request.getSkuId(), request.getFromUnitOfMeasure(),
                request.getToUnitOfMeasure(), request.getStageId(), request.getQuantityToConvert(),
                request.getLocationId(), request.getMessageId());
        return mapToInventoryResponse(inventory);
    }

    public SupplierResponse addSupplier(SupplierRequest request) {
        Supplier supplier = omsService.addSupplier(request.getName(), request.getContactInfo());
        return mapToSupplierResponse(supplier);
    }

    public InwardOrderResponse createInwardOrder(InwardOrderRequest request) {
        // Map request items to model items
        // OmsService expects List<InwardOrderItem>
        List<InwardOrderItem> items = request.getItems().stream().map(this::mapToInwardOrderItemModel)
                .collect(Collectors.toList());
        InwardOrder order = omsService.createInwardOrder(request.getSupplierId(), request.getLocationId(), items);
        return mapToInwardOrderResponse(order);
    }

    public InwardOrderResponse updateOrderStatus(Long orderId, com.example.commons.enums.OrderStatus status) {
        InwardOrder order = omsService.updateOrderStatus(orderId, status);
        return mapToInwardOrderResponse(order);
    }

    public ProductUomResponse addProductUom(ProductUomRequest request) {
        ProductUom productUom = omsService.addProductUom(request.getSkuId(), request.getUnitOfMeasure());
        return mapToProductUomResponse(productUom);
    }

    public GrnResponse createGrn(Long orderId, List<GrnItemRequest> itemRequests) {
        // Map requests to model items
        List<GrnItem> items = itemRequests.stream().map(this::mapToGrnItemModel).collect(Collectors.toList());
        Grn grn = omsService.createGrn(orderId, items);
        return mapToGrnResponse(grn);
    }

    public CustomerResponse addCustomer(CustomerRequest request) {
        Customer customer = omsService.addCustomer(request.getName(), request.getContactInfo());
        return mapToCustomerResponse(customer);
    }

    public PurchaseOrderResponse createPurchaseOrder(PurchaseOrderRequest request) {
        List<PurchaseOrderItem> items = request.getItems().stream().map(this::mapToPurchaseOrderItemModel)
                .collect(Collectors.toList());
        PurchaseOrder order = omsService.createPurchaseOrder(request.getCustomerId(), request.getLocationId(), items);
        return mapToPurchaseOrderResponse(order);
    }

    public PurchaseOrderResponse getPurchaseOrderById(Long orderId) {
        PurchaseOrder order = omsService.getPurchaseOrderById(orderId);
        return mapToPurchaseOrderResponse(order);
    }

    public ProductResponse getProduct(String skuId) {
        Product product = omsService.getProduct(skuId);
        return mapToProductResponse(product);
    }

    public InwardOrderResponse getInwardOrder(Long orderId) {
        InwardOrder order = omsService.getInwardOrder(orderId);
        return mapToInwardOrderResponse(order);
    }

    // Mappers

    private StorageLocationResponse mapToStorageLocationResponse(StorageLocation location) {
        return new StorageLocationResponse(location.getId(), location.getType(), location.getDescription());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(product.getSkuId());
    }

    private InventoryResponse mapToInventoryResponse(Inventory inventory) {
        return new InventoryResponse(inventory.getId(), inventory.getSkuId(), inventory.getUnitOfMeasure(),
                inventory.getLocationId(), inventory.getStageId(), inventory.getQuantity());
    }

    private SupplierResponse mapToSupplierResponse(Supplier supplier) {
        return new SupplierResponse(supplier.getId(), supplier.getName(), supplier.getContactInfo());
    }

    private InwardOrderItem mapToInwardOrderItemModel(InwardOrderItemRequest request) {
        return new InwardOrderItem(request.getSkuId(), request.getQuantity());
    }

    private InwardOrderResponse mapToInwardOrderResponse(InwardOrder order) {
        List<InwardOrderItemResponse> itemResponses = order.getItems().stream()
                .map(item -> new InwardOrderItemResponse(item.getId(), item.getSkuId(), item.getQuantity()))
                .collect(Collectors.toList());
        return new InwardOrderResponse(order.getId(), order.getSupplier().getId(), order.getStatus(),
                order.getLocationId(), itemResponses);
    }

    private ProductUomResponse mapToProductUomResponse(ProductUom productUom) {
        return new ProductUomResponse(productUom.getId(), productUom.getSkuId(),
                productUom.getUnitOfMeasure());
    }

    private GrnItem mapToGrnItemModel(GrnItemRequest request) {
        return new GrnItem(request.getSkuId(), request.getUnitOfMeasure(), request.getQuantity());
    }

    private GrnResponse mapToGrnResponse(Grn grn) {
        List<GrnItemResponse> itemResponses = grn.getItems().stream()
                .map(item -> new GrnItemResponse(item.getId(), item.getSkuId(), item.getUnitOfMeasure(),
                        item.getQuantity()))
                .collect(Collectors.toList());
        return new GrnResponse(grn.getId(), grn.getOrder().getId(), grn.getReceivedDate(), itemResponses);
    }

    private CustomerResponse mapToCustomerResponse(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getContactInfo());
    }

    private PurchaseOrderItem mapToPurchaseOrderItemModel(PurchaseOrderItemRequest request) {
        return new PurchaseOrderItem(request.getSkuId(), request.getUnitOfMeasure(), request.getQuantity());
    }

    private PurchaseOrderResponse mapToPurchaseOrderResponse(PurchaseOrder order) {
        List<PurchaseOrderItemResponse> itemResponses = order.getItems().stream()
                .map(item -> new PurchaseOrderItemResponse(item.getId(), item.getSkuId(), item.getUnitOfMeasure(),
                        item.getQuantity()))
                .collect(Collectors.toList());
        return new PurchaseOrderResponse(order.getId(), order.getCustomer().getId(), order.getLocationId(),
                order.getFulfillableStatus(), itemResponses);
    }
}
