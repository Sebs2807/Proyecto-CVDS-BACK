package edu.eci.cvds.Library.authorization;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import edu.eci.cvds.library.configuration.RestTemplateConfig;
import edu.eci.cvds.library.model.TokenResponse;
import edu.eci.cvds.library.service.AuthorizationService;

@Import(RestTemplateConfig.class)
class AuthorizationServiceTest {

    @Mock
    private RestTemplate restTemplate;

    private AuthorizationService authorizationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authorizationService = new AuthorizationService(restTemplate);
    }

    @Test
    void testValidateToken_ValidToken() {
        String token = "valid_token";
        TokenResponse expectedResponse = new TokenResponse();
        TokenResponse.Data data = new TokenResponse.Data();
        data.setExp(System.currentTimeMillis()); 
        expectedResponse.setData(data);

        when(restTemplate.exchange(
                eq("https://zw8dshmxwa.execute-api.us-east-1.amazonaws.com/BiblioSoft/auth/session"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(TokenResponse.class))
        ).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        TokenResponse result = authorizationService.validateToken(token);

        assertNotNull(result);
        assertTrue(result.isValid());
        verify(restTemplate, times(1)).exchange(
                eq("https://zw8dshmxwa.execute-api.us-east-1.amazonaws.com/BiblioSoft/auth/session"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(TokenResponse.class)
        );  
    }

    @Test
    void testValidateToken_InvalidToken() {
        String token = "invalid_token";
        TokenResponse expectedResponse = new TokenResponse();
        TokenResponse.Data data = new TokenResponse.Data();
        data.setExp(System.currentTimeMillis()/1000);
        expectedResponse.setData(data);

        when(restTemplate.exchange(
                eq("https://zw8dshmxwa.execute-api.us-east-1.amazonaws.com/BiblioSoft/auth/session"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(TokenResponse.class))
        ).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        TokenResponse result = authorizationService.validateToken(token);

        assertNotNull(result);
        assertFalse(result.isValid());
    }
}

