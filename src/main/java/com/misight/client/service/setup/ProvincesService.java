package com.misight.client.service.setup;

import com.misight.client.model.*;
import com.misight.client.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Service
public class ProvincesService {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    @Autowired
    public ProvincesService(RestTemplate restTemplate, @Value("${api.base.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl + "/api/provinces";
    }

    public List<Provinces> getAllProvinces() {
        return restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Provinces>>() {}
        ).getBody();
    }

    public Optional<Provinces> getProvinceById(Long id) {
        try {
            return Optional.ofNullable(restTemplate.getForObject(baseUrl + "/{id}", Provinces.class, id));
        } catch (Exception e) {
            throw new ResourceNotFoundException("Province not found with id: " + id);
        }
    }

    public List<Provinces> getProvincesByRegion(String region) {
        return restTemplate.exchange(
                baseUrl + "/region/{region}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Provinces>>() {},
                region
        ).getBody();
    }

    public Provinces createProvince(Provinces province) {
        return restTemplate.postForObject(baseUrl, province, Provinces.class);
    }

    public void updateProvince(Long id, Provinces province) {
        restTemplate.put(baseUrl + "/{id}", province, id);
    }

    public void deleteProvince(Long id) {
        restTemplate.delete(baseUrl + "/{id}", id);
    }
}