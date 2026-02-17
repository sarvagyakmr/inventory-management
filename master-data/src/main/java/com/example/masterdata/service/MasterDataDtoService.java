package com.example.masterdata.service;

import com.example.commons.dto.oms.CustomerRequest;
import com.example.commons.dto.oms.CustomerResponse;
import com.example.commons.dto.oms.SupplierRequest;
import com.example.commons.dto.oms.SupplierResponse;
import com.example.masterdata.model.Customer;
import com.example.masterdata.model.Supplier;
import org.springframework.stereotype.Service;

/**
 * DTO service layer for master-data module.
 * Handles mapping between requests/responses (from commons DTOs) and master data models.
 * Moved addCustomer/addSupplier flows from OMS.
 */
@Service
public class MasterDataDtoService {

    private final MasterDataService masterDataService;

    public MasterDataDtoService(MasterDataService masterDataService) {
        this.masterDataService = masterDataService;
    }

    public SupplierResponse addSupplier(SupplierRequest request) {
        Supplier supplier = masterDataService.addSupplier(request.getName(), request.getContactInfo());
        return mapToSupplierResponse(supplier);
    }

    public CustomerResponse addCustomer(CustomerRequest request) {
        Customer customer = masterDataService.addCustomer(request.getName(), request.getContactInfo());
        return mapToCustomerResponse(customer);
    }

    /**
     * Gets supplier by ID for client verification.
     */
    public SupplierResponse getSupplier(Long id) {
        Supplier supplier = masterDataService.getSupplier(id);
        return mapToSupplierResponse(supplier);
    }

    /**
     * Gets customer by ID for client verification.
     */
    public CustomerResponse getCustomer(Long id) {
        Customer customer = masterDataService.getCustomer(id);
        return mapToCustomerResponse(customer);
    }

    private SupplierResponse mapToSupplierResponse(Supplier supplier) {
        return new SupplierResponse(supplier.getId(), supplier.getName(), supplier.getContactInfo());
    }

    private CustomerResponse mapToCustomerResponse(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getContactInfo());
    }
}