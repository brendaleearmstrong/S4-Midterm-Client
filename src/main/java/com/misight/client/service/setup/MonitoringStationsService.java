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
public class MonitoringStationsService {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    @Autowired
    public MonitoringStationsService(RestTemplate restTemplate, @Value("${api.base.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl + "/api/monitoring-stations";
    }

    public List<MonitoringStations> getAllStations() {
        return restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MonitoringStations>>() {}
        ).getBody();
    }

    public Optional<MonitoringStations> getStationById(Long id) {
        try {
            return Optional.ofNullable(restTemplate.getForObject(baseUrl + "/{id}", MonitoringStations.class, id));
        } catch (Exception e) {
            throw new ResourceNotFoundException("Station not found with id: " + id);
        }
    }

    public List<MonitoringStations> getStationsByProvince(Long provinceId) {
        return restTemplate.exchange(
                baseUrl + "/province/{provinceId}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MonitoringStations>>() {},
                provinceId
        ).getBody();
    }

    public MonitoringStations createStation(MonitoringStations station) {
        return restTemplate.postForObject(baseUrl, station, MonitoringStations.class);
    }

    public void updateStation(Long id, MonitoringStations station) {
        restTemplate.put(baseUrl + "/{id}", station, id);
    }

    public void deleteStation(Long id) {
        restTemplate.delete(baseUrl + "/{id}", id);
    }

    public MonitoringStations updateStationPollutants(Long stationId, List<Pollutants> pollutants) {
        return restTemplate.postForObject(baseUrl + "/{id}/pollutants", pollutants, MonitoringStations.class, stationId);
    }
}