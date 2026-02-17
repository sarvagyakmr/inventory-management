package com.example.commons.client;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * Abstract base class for REST clients using RestTemplate.
 * Subclasses can extend this to build specific clients.
 * 
 * @param <REQ>  the type of the request object (use Void or Object for no request)
 * @param <RESP> the type of the response object
 */
public abstract class AbstractRestClient<REQ, RESP> {

    protected final RestTemplate restTemplate;
    protected final String baseUrl;

    /**
     * Constructor with base URL for the REST service.
     * 
     * @param baseUrl the base URL of the service (e.g., "http://localhost:8080")
     */
    protected AbstractRestClient(String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
    }

    /**
     * Executes a REST call with the given path, HTTP method, and request body.
     * Supports GET and POST as specified.
     * Updated responseType to Class<? extends RESP> for flexibility in subclasses
     * (e.g., using Object generic but specific response classes).
     * 
     * @param path         the path relative to baseUrl (e.g., "api/resource")
     * @param method       the HTTP method (GET or POST)
     * @param request      the request body (null for GET)
     * @param responseType the class of the response type (subclass of RESP)
     * @return the response object of type RESP
     */
    protected RESP execute(String path, HttpMethod method, REQ request, Class<? extends RESP> responseType) {
        String url = baseUrl + (path.startsWith("/") ? path.substring(1) : path);
        if (method == HttpMethod.GET) {
            return restTemplate.getForObject(url, responseType);
        } else if (method == HttpMethod.POST) {
            return restTemplate.postForObject(url, request, responseType);
        } else {
            throw new UnsupportedOperationException("HTTP method not supported: " + method);
        }
    }

    /**
     * Convenience method for calls without a request body (e.g., GET).
     * Updated responseType to Class<? extends RESP> for flexibility.
     * 
     * @param path         the path relative to baseUrl
     * @param method       the HTTP method (typically GET)
     * @param responseType the class of the response type (subclass of RESP)
     * @return the response object of type RESP
     */
    protected RESP execute(String path, HttpMethod method, Class<? extends RESP> responseType) {
        return execute(path, method, null, responseType);
    }
}
