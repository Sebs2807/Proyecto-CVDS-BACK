package edu.eci.cvds.library.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.eci.cvds.library.model.TokenResponse;

@Service
public class AuthorizationService {

    private final RestTemplate restTemplate;

    public AuthorizationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TokenResponse validateToken(String token) {
        String apiUrl = "https://zw8dshmxwa.execute-api.us-east-1.amazonaws.com/BiblioSoft/auth/session";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<TokenResponse> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                TokenResponse.class
        );
        return response.getBody();
    }
}
