package com.vivelibre.prueba.vivelibre.service;

import com.vivelibre.prueba.vivelibre.controller.request.LoginData;
import com.vivelibre.prueba.vivelibre.model.Usuario;
import com.vivelibre.prueba.vivelibre.repository.UsuariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class UsuariosService {
    private final UsuariosRepository usuariosRepo;
    public Usuario login(LoginData data) throws NoSuchAlgorithmException {
        return usuariosRepo.findFirstByCorreoAndPassword(data.getCorreo(), encodePassword(data.getPassword()));
    }

    public Usuario insert(Usuario usuario) throws NoSuchAlgorithmException {
        usuario.setPassword(encodePassword(usuario.getPassword()));
        return usuariosRepo.save(usuario);
    }

    private String encodePassword(String pass) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(hash);
	}
}

