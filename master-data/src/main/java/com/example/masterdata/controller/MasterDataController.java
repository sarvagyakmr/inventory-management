package com.example.masterdata.controller;

import com.example.commons.dto.oms.CustomerRequest;
import com.example.commons.dto.oms.CustomerResponse;
import com.example.commons.dto.oms.SupplierRequest;
import com.example.commons.dto.oms.SupplierResponse;
import com.example.masterdata.service.MasterDataDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for master data APIs, moved from OMS.
 * Endpoints for creating Customer and Supplier.
 * Base path /api/master-data for the dedicated service.
 */
@RestController
@RequestMapping("/api/master-data")
public class MasterDataController {

    private final MasterDataDtoService masterDataDtoService;

    public MasterDataController(MasterDataDtoService masterDataDtoService) {
        this.masterDataDtoService = masterDataDtoService;
    }

    @PostMapping("/suppliers/add")
    public ResponseEntity<SupplierResponse> addSupplier(@RequestBody SupplierRequest request) {
        SupplierResponse supplier = masterDataDtoService.addSupplier(request);
        return ResponseEntity.ok(supplier);
    }

    @PostMapping("/customers/add")
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest request) {
        CustomerResponse customer = masterDataDtoService.addCustomer(request);
        return ResponseEntity.ok(customer);
    }

    /**
     * Gets supplier by ID for client verification (e.g., OMS inward order).
     */
    @GetMapping("/suppliers/{id}")
    public ResponseEntity<SupplierResponse> getSupplier(@PathVariable Long id) {
        SupplierResponse supplier = masterDataDtoService.getSupplier(id);
        return ResponseEntity.ok(supplier);
    }

    /**
     * Gets customer by ID for client verification (e.g., OMS outward order).
     */
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id) {
        CustomerResponse customer = masterDataDtoService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }
}