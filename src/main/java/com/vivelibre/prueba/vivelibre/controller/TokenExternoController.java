package com.vivelibre.prueba.vivelibre.controller;


import com.vivelibre.prueba.vivelibre.service.TokenExternoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenExternoController {

	private final TokenExternoService tokenExternoService;

	@PostMapping
	public ResponseEntity<?> obtenerTokenExterno() {
		try {
			String token = tokenExternoService.obtenerToken();
			if (token == null || token.trim().isEmpty()) {
				return ResponseEntity.badRequest().body(Map.of("Error", "No se pudo obtener el token."));
			}

			return ResponseEntity.ok(tokenExternoService.getTokenResponse(token));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Map.of("Error", "Error crítico al intentar obtener el token."));
		}
	}

}