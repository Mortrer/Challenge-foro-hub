package com.aluralatam_nils.Foro_hub.security;


import com.aluralatam_nils.Foro_hub.domain.Rol.Rol;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private final String SECRET = "MiSecretoSuperSecreto"; // Cambiar por variable de entorno
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    public String generateToken(String email, Rol rol) {
        return JWT.create()
                .withSubject(email)
                .withClaim("rol", rol.name())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public boolean validarToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String obtenerEmail(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    public String obtenerRol(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaim("rol").asString();
    }
}