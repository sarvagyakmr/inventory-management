package com.example.commons.client.oms;

import com.example.commons.client.AbstractRestClient;
import com.example.commons.dto.oms.InwardOrderResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;

/**
 * OMS (Order Management System) client extending AbstractRestClient.
 * Provides methods to interact with OMS REST APIs using shared DTOs from commons.
 * Can be extended for additional endpoints (e.g., GRN, products).
 *
 * Note: @Component removed as bean is explicitly defined in wms module's
 * WmsApplication for proper initialization in dependent apps.
 */
public class OmsClient extends AbstractRestClient<Void, InwardOrderResponse> {

    /**
     * Constructor takes the OMS base URL (appended with /api/oms for endpoints).
     * The @Value is retained for compatibility if used elsewhere, but resolved
     * via @Bean in wms module.
     * 
     * @param omsBaseUrl the base URL for OMS service
     */
    public OmsClient(@Value("${oms.base-url:http://localhost:8081}/api/oms") String omsBaseUrl) {
        super(omsBaseUrl);
    }

    /**
     * Retrieves an InwardOrder by ID via GET /orders/inward/{orderId}.
     * Used for validation (e.g., during WMS box creation for INWARD types).
     * 
     * @param orderId the ID of the inward order
     * @return InwardOrderResponse from OMS
     */
    public InwardOrderResponse getInwardOrder(Long orderId) {
        // Path as specified; baseUrl handles /api/oms prefix
        String path = "orders/inward/" + orderId;
        return execute(path, HttpMethod.GET, InwardOrderResponse.class);
    }
}
