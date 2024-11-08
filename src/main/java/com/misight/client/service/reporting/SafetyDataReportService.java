package com.misight.client.service.reporting;

import com.misight.client.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class SafetyDataReportService {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    @Autowired
    public SafetyDataReportService(RestTemplate restTemplate, @Value("${api.base.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl + "/api/reports/safety";
    }

    public Map<String, Object> generateSafetyReport(Long mineId, LocalDateTime startDate, LocalDateTime endDate) {
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("mineId", mineId)
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        ).getBody();
    }

    public Map<String, Object> generateIncidentSummary(Long mineId, int year) {
        HttpEntity<?> entity = new HttpEntity<>(createHeaders());
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/incidents")
                .queryParam("mineId", mineId)
                .queryParam("year", year)
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        ).getBody();
    }

    public byte[] exportReport(Map<String, Object> reportData, String format) {
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(reportData, createHeaders());
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/export")
                .queryParam("format", format)
                .encode()
                .toUriString();

        return restTemplate.postForObject(url, entity, byte[].class);
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}