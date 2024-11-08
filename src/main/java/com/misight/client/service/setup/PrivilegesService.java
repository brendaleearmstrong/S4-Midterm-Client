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
public class PrivilegesService {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    @Autowired
    public PrivilegesService(RestTemplate restTemplate, @Value("${api.base.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl + "/api/privileges";
    }

    public List<Privileges> getAllPrivileges() {
        return restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Privileges>>() {}
        ).getBody();
    }

    public Optional<Privileges> getPrivilegeById(Long id) {
        try {
            return Optional.ofNullable(restTemplate.getForObject(baseUrl + "/{id}", Privileges.class, id));
        } catch (Exception e) {
            throw new ResourceNotFoundException("Privilege not found with id: " + id);
        }
    }

    public Privileges createPrivilege(Privileges privilege) {
        return restTemplate.postForObject(baseUrl, privilege, Privileges.class);
    }

    public void updatePrivilege(Long id, Privileges privilege) {
        restTemplate.put(baseUrl + "/{id}", privilege, id);
    }

    public void deletePrivilege(Long id) {
        restTemplate.delete(baseUrl + "/{id}", id);
    }
}