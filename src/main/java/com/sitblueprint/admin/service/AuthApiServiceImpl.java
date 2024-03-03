package com.sitblueprint.admin.service;

import com.sitblueprint.admin.model.AuthUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthApiServiceImpl implements AuthApiService {
    private final RestTemplate restTemplate;

    @Value("${blueprint_yaml.api.baseurl}")
    private String baseUrl;

    public AuthApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public AuthUser updateAuthUser(AuthUser authUser) {
        final String username = authUser.getUsername();
        final String endpoint = "/users/user?username={username}";

        //Map URI variables
        Map<String, String> uriVariables = returnURIWithUsername(username);
        final String url = baseUrl + endpoint;
        final ResponseEntity<AuthUser> response =
                restTemplate
                        .exchange(
                                url,
                                HttpMethod.PUT,
                                null,
                                AuthUser.class
                        );
        return response.getBody();
    }

    @Override
    public AuthUser deleteAuthUser(String username) {
        final String endpoint = "/users/user?username={username}";

        // Map URI variables
        final Map<String, String> uriVariables = returnURIWithUsername(username);
        final String url = baseUrl + endpoint;
        final ResponseEntity<AuthUser> response =
                restTemplate
                        .exchange(
                                url,
                                HttpMethod.DELETE,
                                null,
                                AuthUser.class
                        );
        return response.getBody();
    }

    @Override
    public AuthUser disableAuthUser(String username) {
        final String endpoint = "/users/user/disable?username={username}";
        // Map URI variables
        final Map<String, String> uriVariables = returnURIWithUsername(username);
        final String url = baseUrl + endpoint;
        final ResponseEntity<AuthUser> response =
                restTemplate
                        .exchange(
                                url,
                                HttpMethod.POST,
                                null,
                                AuthUser.class,
                                uriVariables
                        );
        return response.getBody();
    }

    @Override
    public AuthUser enableAuthUser(String username) {
        final String endpoint = "/users/user/enable?username={username}";
        // Map URI variables
        final Map<String, String> uriVariables = returnURIWithUsername(username);
        final String url = baseUrl + endpoint;
        final ResponseEntity<AuthUser> response =
                restTemplate
                        .exchange(
                                url,
                                HttpMethod.POST,
                                null,
                                AuthUser.class,
                                uriVariables
                        );
        return response.getBody();
    }

    @Override
    public AuthUser resetPasswordAuthUser(String username) {
        final String endpoint = "/users/reset_password";
        final String url = baseUrl + endpoint;
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", username);

        // Set Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HTTPEntity Object with headers and body
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make POST request
        final ResponseEntity<AuthUser> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        requestEntity,
                        AuthUser.class
                );
        return response.getBody();
    }

    private Map<String, String> returnURIWithUsername(String username) {
        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("username", username);
        return uriVariables;
    }
}
