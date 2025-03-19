package com.vivelibre.prueba.vivelibre.security;

import java.security.Key;
import java.util.Base64;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.crypto.spec.SecretKeySpec;

public class SecurityConstants {
	private static final String SECRET_STRING = "asfda21424safsfaraJWT1234567890asdf1234567890";
	public static final Key SECRET_KEY = new SecretKeySpec(
			Base64.getEncoder().encode(SECRET_STRING.getBytes()),
			SignatureAlgorithm.HS512.getJcaName()
	);
}
