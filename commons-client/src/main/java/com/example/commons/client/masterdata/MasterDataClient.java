package com.example.commons.client.masterdata;

import com.example.commons.client.AbstractRestClient;
import com.example.commons.dto.oms.CustomerRequest;
import com.example.commons.dto.oms.CustomerResponse;
import com.example.commons.dto.oms.SupplierRequest;
import com.example.commons.dto.oms.SupplierResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * Master Data client extending AbstractRestClient.
 * Provides methods for createSupplier and createCustomer (moved APIs).
 * Used by OMS for verification in order creation.
 */
@Component
public class MasterDataClient extends AbstractRestClient<Object, Object> {

    /**
     * Constructor with base URL from properties.
     * Defaults to master-data service at http://localhost:8083/api/master-data
     * 
     * @param masterDataBaseUrl base URL for master-data service
     */
    public MasterDataClient(@Value("${masterdata.base-url:http://localhost:8083}/api/master-data") String masterDataBaseUrl) {
        super(masterDataBaseUrl);
    }

    /**
     * Creates a supplier via POST /suppliers/add
     * 
     * @param request SupplierRequest
     * @return SupplierResponse
     */
    public SupplierResponse createSupplier(SupplierRequest request) {
        // Cast from Object response type (for generic flexibility)
        return (SupplierResponse) execute("suppliers/add", HttpMethod.POST, request, SupplierResponse.class);
    }

    /**
     * Creates a customer via POST /customers/add
     * 
     * @param request CustomerRequest
     * @return CustomerResponse
     */
    public CustomerResponse createCustomer(CustomerRequest request) {
        return (CustomerResponse) execute("customers/add", HttpMethod.POST, request, CustomerResponse.class);
    }

    /**
     * Gets supplier by ID via GET /suppliers/{id} . Used for existence verification (e.g., OMS inward orders).
     * 
     * @param id supplier ID
     * @return SupplierResponse
     */
    public SupplierResponse getSupplier(Long id) {
        String path = "suppliers/" + id;
        // Cast from Object response type
        return (SupplierResponse) execute(path, HttpMethod.GET, SupplierResponse.class);
    }

    /**
     * Gets customer by ID via GET /customers/{id} . Used for existence verification (e.g., OMS outward orders).
     * 
     * @param id customer ID
     * @return CustomerResponse
     */
    public CustomerResponse getCustomer(Long id) {
        String path = "customers/" + id;
        return (CustomerResponse) execute(path, HttpMethod.GET, CustomerResponse.class);
    }
}