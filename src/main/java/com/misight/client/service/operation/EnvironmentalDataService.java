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
public class EnvironmentalDataService {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    @Autowired
    public EnvironmentalDataService(RestTemplate restTemplate, @Value("${api.base.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl + "/api/environmental-data";
    }

    public List<EnvironmentalData> getAllData() {
        try {
            HttpEntity<?> entity = new HttpEntity<>(createHeaders());
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .build()
                    .toUriString();

            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<EnvironmentalData>>() {}
            ).getBody();
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch environmental data: " + e.getMessage());
        }
    }

    public Optional<EnvironmentalData> getDataById(Long id) {
        try {
            HttpEntity<?> entity = new HttpEntity<>(createHeaders());
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/{id}")
                    .buildAndExpand(id)
                    .toUriString();

            ResponseEntity<EnvironmentalData> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    EnvironmentalData.class
            );
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Environmental data not found with id: " + id);
        }
    }

    public List<EnvironmentalData> getDataByDateRange(LocalDateTime start, LocalDateTime end) {
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
                    new ParameterizedTypeReference<List<EnvironmentalData>>() {}
            ).getBody();
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch data by date range: " + e.getMessage());
        }
    }

    public EnvironmentalData createData(EnvironmentalData data) {
        try {
            HttpEntity<EnvironmentalData> entity = new HttpEntity<>(data, createHeaders());
            return restTemplate.postForObject(baseUrl, entity, EnvironmentalData.class);
        } catch (Exception e) {
            throw new ServiceException("Failed to create environmental data: " + e.getMessage());
        }
    }

    public void updateData(Long id, EnvironmentalData data) {
        try {
            HttpEntity<EnvironmentalData> entity = new HttpEntity<>(data, createHeaders());
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
            throw new ServiceException("Failed to update environmental data: " + e.getMessage());
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
            throw new ServiceException("Failed to delete environmental data: " + e.getMessage());
        }
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}