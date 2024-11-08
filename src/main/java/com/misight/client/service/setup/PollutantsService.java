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
public class PollutantsService {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    @Autowired
    public PollutantsService(RestTemplate restTemplate, @Value("${api.base.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl + "/api/pollutants";
    }

    public List<Pollutants> getAllPollutants() {
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());
        return restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Pollutants>>() {}
        ).getBody();
    }

    public Optional<Pollutants> getPollutantById(Long id) {
        try {
            HttpEntity<?> entity = new HttpEntity<>(createHeaders());
            return Optional.ofNullable(restTemplate.exchange(
                    baseUrl + "/{id}",
                    HttpMethod.GET,
                    entity,
                    Pollutants.class,
                    id
            ).getBody());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Pollutant not found with id: " + id);
        }
    }

    public List<Pollutants> getPollutantsByCategory(String category) {
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/category")
                .queryParam("category", category)
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Pollutants>>() {}
        ).getBody();
    }

    public Pollutants createPollutant(Pollutants pollutant) {
        HttpEntity<Pollutants> entity = new HttpEntity<>(pollutant, createHeaders());
        return restTemplate.postForObject(baseUrl, entity, Pollutants.class);
    }

    public void updatePollutant(Long id, Pollutants pollutant) {
        HttpEntity<Pollutants> entity = new HttpEntity<>(pollutant, createHeaders());
        restTemplate.exchange(
                baseUrl + "/{id}",
                HttpMethod.PUT,
                entity,
                Void.class,
                id
        );
    }

    public void deletePollutant(Long id) {
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());
        restTemplate.exchange(
                baseUrl + "/{id}",
                HttpMethod.DELETE,
                entity,
                Void.class,
                id
        );
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}