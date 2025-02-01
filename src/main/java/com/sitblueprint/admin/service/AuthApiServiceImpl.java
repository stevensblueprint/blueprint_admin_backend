package com.sitblueprint.admin.service;

import com.sitblueprint.admin.model.AuthMember;
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
	public AuthMember createAuthMember(AuthMember member) {
		final String endpoint = "/members/member";
		final String url = baseUrl + endpoint;

		// Set Headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Create HTTP Entity
		HttpEntity<AuthMember> requestEntity = new HttpEntity<>(member, headers);

		ResponseEntity<AuthMember> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
				AuthMember.class);
		return response.getBody();
	}

	@Override
	public AuthMember updateAuthMember(AuthMember member) {
		final String username = member.getUsername();
		final String endpoint = "/members/member?username={username}";

		// Map URI variables
		Map<String, String> uriVariables = returnURIWithUsername(username);
		final String url = baseUrl + endpoint;
		final ResponseEntity<AuthMember> response = restTemplate.exchange(url, HttpMethod.PUT, null, AuthMember.class);
		return response.getBody();
	}

	@Override
	public AuthMember deleteAuthMember(String username) {
		final String endpoint = "/members/member?username={username}";

		// Map URI variables
		final Map<String, String> uriVariables = returnURIWithUsername(username);
		final String url = baseUrl + endpoint;
		final ResponseEntity<AuthMember> response = restTemplate.exchange(url, HttpMethod.DELETE, null,
				AuthMember.class);
		return response.getBody();
	}

	@Override
	public AuthMember disableAuthMember(String username) {
		final String endpoint = "/members/member/disable?username={username}";
		// Map URI variables
		final Map<String, String> uriVariables = returnURIWithUsername(username);
		final String url = baseUrl + endpoint;
		final ResponseEntity<AuthMember> response = restTemplate.exchange(url, HttpMethod.POST, null, AuthMember.class,
				uriVariables);
		return response.getBody();
	}

	@Override
	public AuthMember enableAuthMember(String username) {
		final String endpoint = "/members/member/enable?username={username}";
		// Map URI variables
		final Map<String, String> uriVariables = returnURIWithUsername(username);
		final String url = baseUrl + endpoint;
		final ResponseEntity<AuthMember> response = restTemplate.exchange(url, HttpMethod.POST, null, AuthMember.class,
				uriVariables);
		return response.getBody();
	}

	@Override
	public AuthMember resetPasswordAuthMember(String username, String password) {
		final String endpoint = "/members/reset_password";
		final String url = baseUrl + endpoint;
		Map<String, String> requestBody = new HashMap<>();
		requestBody.put("username", username);
		requestBody.put("password", password);

		// Set Headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Create HTTPEntity Object with headers and body
		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

		// Make POST request
		final ResponseEntity<AuthMember> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
				AuthMember.class);
		return response.getBody();
	}

	private Map<String, String> returnURIWithUsername(String username) {
		final Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("username", username);
		return uriVariables;
	}
}
