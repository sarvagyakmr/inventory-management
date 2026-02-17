package com.example.masterdata.service;

import com.example.masterdata.model.Customer;
import com.example.masterdata.model.Supplier;
import com.example.masterdata.repository.CustomerRepository;
import com.example.masterdata.repository.SupplierRepository;
import org.springframework.stereotype.Service;

/**
 * Core service for master data operations in master-data module.
 * Handles CRUD for Customer and Supplier (moved from OMS).
 */
@Service
public class MasterDataService {

    private final SupplierRepository supplierRepository;
    private final CustomerRepository customerRepository;

    public MasterDataService(SupplierRepository supplierRepository, CustomerRepository customerRepository) {
        this.supplierRepository = supplierRepository;
        this.customerRepository = customerRepository;
    }

    public Supplier addSupplier(String name, String contactInfo) {
        Supplier supplier = new Supplier(name, contactInfo);
        return supplierRepository.save(supplier);
    }

    public Customer addCustomer(String name, String contactInfo) {
        Customer customer = new Customer(name, contactInfo);
        return customerRepository.save(customer);
    }

    /**
     * Gets supplier by ID. Used by clients for existence verification (e.g., in OMS inward order creation).
     * Throws if not found.
     */
    public Supplier getSupplier(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found: " + id));
    }

    /**
     * Gets customer by ID. Used by clients for existence verification (e.g., in OMS outward order creation).
     * Throws if not found.
     */
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + id));
    }
}