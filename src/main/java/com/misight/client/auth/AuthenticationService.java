package com.misight.client.auth;

import com.misight.client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    private final RestTemplate restTemplate;
    private final String baseUrl;
    private String currentToken;
    private User currentUser;

    @Autowired
    public AuthenticationService(RestTemplate restTemplate, @Value("${api.base.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl + "/api/auth";
    }

    public boolean login(String username, String password) {
        try {
            Map<String, String> credentials = new HashMap<>();
            credentials.put("username", username);
            credentials.put("password", password);

            ResponseEntity<LoginResponse> response = restTemplate.postForEntity(
                    baseUrl + "/login",
                    credentials,
                    LoginResponse.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                this.currentToken = response.getBody().getToken();
                this.currentUser = response.getBody().getUser();
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        if (currentToken != null) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setBearerAuth(currentToken);
                HttpEntity<?> entity = new HttpEntity<>(headers);

                restTemplate.exchange(
                        baseUrl + "/logout",
                        HttpMethod.POST,
                        entity,
                        Void.class
                );
            } finally {
                currentToken = null;
                currentUser = null;
            }
        }
    }

    public Optional<User> getCurrentUser() {
        return Optional.ofNullable(currentUser);
    }

    public String getCurrentToken() {
        return currentToken;
    }

    public boolean isAuthenticated() {
        return currentToken != null && currentUser != null;
    }

    public HttpHeaders getAuthenticatedHeaders() {
        HttpHeaders headers = new HttpHeaders();
        if (currentToken != null) {
            headers.setBearerAuth(currentToken);
        }
        return headers;
    }

    private static class LoginResponse {
        private String token;
        private User user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
}
