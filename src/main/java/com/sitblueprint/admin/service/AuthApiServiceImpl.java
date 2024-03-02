package com.sitblueprint.admin.service;

import com.sitblueprint.admin.model.AuthUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AuthApiServiceImpl implements AuthApiService {
    private final RestTemplate restTemplate;

    @Value("${blueprint_yaml.api.baseurl}")
    private String baseUrl;

    public AuthApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<AuthUser> getAllAuthUsers() {
        final String endpoint = "/users/all";
        final String url = baseUrl + endpoint;
        final ResponseEntity<List<AuthUser>> response =
                restTemplate
                        .exchange(
                                url,
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<List<AuthUser>>() {}
                        );
        return response.getBody();
    }

    @Override
    public AuthUser getAuthUser(String username) {
        final String endpoint = "/users/user?username={username}";
        final String url = baseUrl + endpoint;
        final ResponseEntity<AuthUser> response =
                restTemplate
                        .getForEntity(
                                url,
                                AuthUser.class
                        );
        return response.getBody();
    }

    @Override
    public AuthUser updateAuthUser(AuthUser authUser) {
        return null;
    }

    @Override
    public AuthUser deleteAuthUser(String username) {
        return null;
    }

    @Override
    public AuthUser disableAuthUser(String username) {
        return null;
    }

    @Override
    public AuthUser enableAuthUser(String username) {
        return null;
    }

    @Override
    public AuthUser resetPasswordAuthUser(String username) {
        return null;
    }
}
