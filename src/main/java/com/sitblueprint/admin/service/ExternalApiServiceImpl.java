package com.sitblueprint.admin.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiServiceImpl implements ExternalApiService {
    private final RestTemplate restTemplate;

    @Value("${blueprint_yaml.api.baseurl}")
    private String baseUrl;

    public ExternalApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
