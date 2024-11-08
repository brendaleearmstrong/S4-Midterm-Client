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
import java.util.Set;

@Service
public class UserService {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    @Autowired
    public UserService(RestTemplate restTemplate, @Value("${api.base.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl + "/api/users";
    }

    public List<User> getAllUsers() {
        return restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {}
        ).getBody();
    }

    public Optional<User> getUserById(Long id) {
        try {
            return Optional.ofNullable(restTemplate.getForObject(baseUrl + "/{id}", User.class, id));
        } catch (Exception e) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

    public Optional<User> getUserByUsername(String username) {
        try {
            return Optional.ofNullable(restTemplate.getForObject(baseUrl + "/username/{username}", User.class, username));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public User createUser(User user) {
        return restTemplate.postForObject(baseUrl, user, User.class);
    }

    public void updateUser(Long id, User user) {
        restTemplate.put(baseUrl + "/{id}", user, id);
    }

    public void deleteUser(Long id) {
        restTemplate.delete(baseUrl + "/{id}", id);
    }

    public void updateUserPrivileges(Long userId, Set<Privileges> privileges) {
        restTemplate.put(baseUrl + "/{id}/privileges", privileges, userId);
    }
}