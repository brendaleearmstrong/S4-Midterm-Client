package com.misight.client.service.operation;

import com.misight.client.model.*;
import com.misight.client.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SafetyDataService {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    @Autowired
    public SafetyDataService(RestTemplate restTemplate, @Value("${api.base.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl + "/api/safety-data";
    }

    public List<SafetyData> getAllData() {
        try {
            HttpEntity<?> entity = new HttpEntity<>(createHeaders());
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .build()
                    .toUriString();

            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<SafetyData>>() {}
            ).getBody();
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch safety data: " + e.getMessage());
        }
    }

    public Optional<SafetyData> getDataById(Long id) {
        try {
            HttpEntity<?> entity = new HttpEntity<>(createHeaders());
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/{id}")
                    .buildAndExpand(id)
                    .toUriString();

            ResponseEntity<SafetyData> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    SafetyData.class
            );
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Safety data not found with id: " + id);
        }
    }

    public List<SafetyData> getDataByMine(Long mineId) {
        try {
            HttpEntity<?> entity = new HttpEntity<>(createHeaders());
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/mine/{mineId}")
                    .buildAndExpand(mineId)
                    .toUriString();

            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<SafetyData>>() {}
            ).getBody();
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch safety data for mine: " + e.getMessage());
        }
    }

    public List<SafetyData> getDataByDateRange(LocalDateTime start, LocalDateTime end) {
        try {
            HttpEntity<?> entity = new HttpEntity<>(createHeaders());
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/range")
                    .queryParam("start", start)
                    .queryParam("end", end)
                    .build()
                    .toUriString();

            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<SafetyData>>() {}
            ).getBody();
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch safety data by date range: " + e.getMessage());
        }
    }

    public SafetyData createData(SafetyData data) {
        try {
            HttpEntity<SafetyData> entity = new HttpEntity<>(data, createHeaders());
            return restTemplate.postForObject(baseUrl, entity, SafetyData.class);
        } catch (Exception e) {
            throw new ServiceException("Failed to create safety data: " + e.getMessage());
        }
    }

    public void updateData(Long id, SafetyData data) {
        try {
            HttpEntity<SafetyData> entity = new HttpEntity<>(data, createHeaders());
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/{id}")
                    .buildAndExpand(id)
                    .toUriString();

            restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    entity,
                    Void.class
            );
        } catch (Exception e) {
            throw new ServiceException("Failed to update safety data: " + e.getMessage());
        }
    }

    public void deleteData(Long id) {
        try {
            HttpEntity<?> entity = new HttpEntity<>(createHeaders());
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/{id}")
                    .buildAndExpand(id)
                    .toUriString();

            restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    entity,
                    Void.class
            );
        } catch (Exception e) {
            throw new ServiceException("Failed to delete safety data: " + e.getMessage());
        }
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}