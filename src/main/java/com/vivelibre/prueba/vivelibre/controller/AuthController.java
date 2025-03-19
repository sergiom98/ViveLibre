package com.vivelibre.prueba.vivelibre.controller;


import com.vivelibre.prueba.vivelibre.controller.request.LoginData;
import com.vivelibre.prueba.vivelibre.controller.response.TokenRespuestaPropio;
import com.vivelibre.prueba.vivelibre.model.Usuario;
import com.vivelibre.prueba.vivelibre.security.SecurityConstants;
import com.vivelibre.prueba.vivelibre.service.UsuariosService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UsuariosService usuariosService;

    @PostMapping("/login")
    public ResponseEntity<TokenRespuestaPropio> postMethodName(@RequestBody @Valid LoginData loginData) throws NoSuchAlgorithmException {
        Usuario usuario = usuariosService.login(loginData);

		if(usuario != null) { // Login OK
			return ResponseEntity.ok().body(new TokenRespuestaPropio(getToken(usuario)));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
    }

    @PostMapping("/registro")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody @Valid Usuario usuario) throws NoSuchAlgorithmException {
        usuariosService.insert(usuario);
    }

    private String getToken(Usuario user) {
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("id", user.getId());
		data.put("correo", user.getCorreo());
		data.put("authorities", Arrays.asList("ROLE_USER"));

		return Jwts.builder().setId("viveLibre")
				.setSubject(user.getNombre()).addClaims(data)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 30*24*60*60)) // Caduca en un mes
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY).compact();

	}
}