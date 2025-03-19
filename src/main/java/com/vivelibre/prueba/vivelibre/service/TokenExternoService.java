package com.vivelibre.prueba.vivelibre.service;


import com.vivelibre.prueba.vivelibre.controller.response.TokenResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Service
public class TokenExternoService {

    private final WebClient webClient;

    public TokenExternoService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public String obtenerToken() {
        return webClient.post()
                .uri("/token")
                .header("Content-Type", "application/json")
                .bodyValue("{\"username\":\"auth-vivelibre\", \"password\":\"password\"}")
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .map(TokenResponse::token)
                .block();
    }

    public TokenResponse getTokenResponse(String token) {
        String timestamp = DateTimeFormatter.ISO_INSTANT.format(Instant.now());
        return new TokenResponse(token, "TOKEN_RECIBIDO", timestamp);
    }
}
