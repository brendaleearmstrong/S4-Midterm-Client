package com.misight.client.service.setup;

import com.misight.client.model.*;
import com.misight.client.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class MineralsService {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    @Autowired
    public MineralsService(RestTemplate restTemplate, @Value("${api.base.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl + "/api/minerals";
    }

    public List<Minerals> getAllMinerals() {
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Minerals>>() {}
        ).getBody();
    }

    public Optional<Minerals> getMineralById(Long id) {
        try {
            HttpEntity<?> entity = new HttpEntity<>(createHeaders());
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/{id}")
                    .buildAndExpand(id)
                    .toUriString();

            ResponseEntity<Minerals> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    Minerals.class
            );
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Mineral not found with id: " + id);
        }
    }

    public List<Minerals> getMineralsByMine(Long mineId) {
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/mine/{mineId}")
                .buildAndExpand(mineId)
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Minerals>>() {}
        ).getBody();
    }

    public Minerals createMineral(Minerals mineral) {
        try {
            HttpEntity<Minerals> entity = new HttpEntity<>(mineral, createHeaders());
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .toUriString();

            ResponseEntity<Minerals> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    Minerals.class
            );
            return response.getBody();
        } catch (Exception e) {
            throw new ServiceException("Failed to create mineral: " + e.getMessage());
        }
    }

    public void updateMineral(Long id, Minerals mineral) {
        try {
            HttpEntity<Minerals> entity = new HttpEntity<>(mineral, createHeaders());
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
            throw new ServiceException("Failed to update mineral with id: " + id);
        }
    }

    public void deleteMineral(Long id) {
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
            throw new ServiceException("Failed to delete mineral with id: " + id);
        }
    }

    public List<Minerals> getMineralsByName(String name) {
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/search")
                .queryParam("name", name)
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Minerals>>() {}
        ).getBody();
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}