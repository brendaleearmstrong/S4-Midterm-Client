package com.misight.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.core.ParameterizedTypeReference;
import java.util.List;

@Component
public class RESTClient {
    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String tokenHeader;
    private final String tokenPrefix;

    public RESTClient(
            RestTemplate restTemplate,
            @Value("${api.base.url}") String baseUrl,
            @Value("${security.token.header}") String tokenHeader,
            @Value("${security.token.prefix}") String tokenPrefix
    ) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.tokenHeader = tokenHeader;
        this.tokenPrefix = tokenPrefix;
    }

    public <T> T get(String endpoint, Class<T> responseType) {
        String url = baseUrl + endpoint;
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
        return response.getBody();
    }

    public <T> List<T> getList(String endpoint, ParameterizedTypeReference<List<T>> responseType) {
        String url = baseUrl + endpoint;
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<List<T>> response = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
        return response.getBody();
    }

    public <T> T post(String endpoint, Object request, Class<T> responseType) {
        String url = baseUrl + endpoint;
        HttpEntity<?> entity = new HttpEntity<>(request, createHeaders());
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, entity, responseType);
        return response.getBody();
    }

    public <T> T put(String endpoint, Object request, Class<T> responseType) {
        String url = baseUrl + endpoint;
        HttpEntity<?> entity = new HttpEntity<>(request, createHeaders());
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.PUT, entity, responseType);
        return response.getBody();
    }

    public void delete(String endpoint) {
        String url = baseUrl + endpoint;
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());
        restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}