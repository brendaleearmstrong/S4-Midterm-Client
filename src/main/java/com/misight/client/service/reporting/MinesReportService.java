package com.misight.client.service.reporting;

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
import java.util.Map;

@Service
public class MinesReportService {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    @Autowired
    public MinesReportService(RestTemplate restTemplate, @Value("${api.base.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl + "/api/reports/mines";
    }

    public Map<String, Object> generateMinePerformanceReport(Long mineId, LocalDateTime startDate, LocalDateTime endDate) {
        try {
            HttpEntity<?> entity = new HttpEntity<>(createHeaders());
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/performance")
                    .queryParam("mineId", mineId)
                    .queryParam("startDate", startDate)
                    .queryParam("endDate", endDate)
                    .build()
                    .toUriString();

            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<Map<String, Object>>() {}
            ).getBody();
        } catch (Exception e) {
            throw new ServiceException("Failed to generate performance report: " + e.getMessage());
        }
    }

    public Map<String, Object> generateComplianceReport(Long mineId) {
        try {
            HttpEntity<?> entity = new HttpEntity<>(createHeaders());
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/compliance/{mineId}")
                    .buildAndExpand(mineId)
                    .toUriString();

            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<Map<String, Object>>() {}
            ).getBody();
        } catch (Exception e) {
            throw new ServiceException("Failed to generate compliance report: " + e.getMessage());
        }
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}