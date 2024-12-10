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

    // Constructor que permite inyectar un RestTemplate en la clase
    public AuthorizationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Método para validar un token
    public TokenResponse validateToken(String token) {
        String apiUrl = "https://cvds-api-f4bjcdd7gjb5fffp.eastus-01.azurewebsites.net/auth/session";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);  // Configura el token en los encabezados HTTP
        HttpEntity<String> entity = new HttpEntity<>(headers);  // Crea la entidad HTTP con los encabezados
        ResponseEntity<TokenResponse> response = restTemplate.exchange(
                apiUrl,  // URL de la API
                HttpMethod.GET,  // Método HTTP GET
                entity,  // Entidad que contiene los encabezados
                TokenResponse.class  // Clase de respuesta esperada
        );
        return response.getBody();  // Devuelve el cuerpo de la respuesta (TokenResponse)
    }
}
