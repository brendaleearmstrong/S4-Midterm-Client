package com.misight.client.service.reporting;

import com.misight.client.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Map;

@Service
public class EnvironmentalDataReportService {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    @Autowired
    public EnvironmentalDataReportService(RestTemplate restTemplate, @Value("${api.base.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl + "/api/reports/environmental";
    }

    public Map<String, Object> generateMonthlyReport(Long mineId, int year, int month) {
        try {
            HttpEntity<?> entity = new HttpEntity<>(createHeaders());
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/monthly")
                    .queryParam("mineId", mineId)
                    .queryParam("year", year)
                    .queryParam("month", month)
                    .build()
                    .toUriString();

            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<Map<String, Object>>() {}
            ).getBody();
        } catch (Exception e) {
            throw new ServiceException("Failed to generate monthly report: " + e.getMessage());
        }
    }

    public Map<String, Object> generateYearlyReport(Long mineId, int year) {
        try {
            HttpEntity<?> entity = new HttpEntity<>(createHeaders());
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/yearly")
                    .queryParam("mineId", mineId)
                    .queryParam("year", year)
                    .build()
                    .toUriString();

            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<Map<String, Object>>() {}
            ).getBody();
        } catch (Exception e) {
            throw new ServiceException("Failed to generate yearly report: " + e.getMessage());
        }
    }

    public interface DataPersistenceVerification {
        boolean verifyDataPersistence(Long id);
        void displaySavedData(Long id);
    }

    public byte[] exportReport(Map<String, Object> reportData, String format) {
        try {
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(reportData, createHeaders());
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/export")
                    .queryParam("format", format)
                    .build()
                    .toUriString();

            return restTemplate.postForObject(url, entity, byte[].class);
        } catch (Exception e) {
            throw new ServiceException("Failed to export report: " + e.getMessage());
        }
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}