package com.anosman.roombooking.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JWTService {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
    private long expirationTime = 1800000;

    @PostConstruct
    public void initKeys() throws NoSuchAlgorithmException {

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();
        publicKey = (RSAPublicKey) keyPair.getPublic();
        privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }

    public String generateToken(String name, List<String> roles) {
        return JWT.create()
                .withClaim("user", name)
                .withClaim("roles", roles)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.RSA256(publicKey, privateKey));
    }

    public String validateToken(String token) throws JWTVerificationException {
        String encodedPayload = JWT.require(Algorithm.RSA256(publicKey, privateKey))
                .build()
                .verify(token)
                .getPayload();
        return new String(Base64.getDecoder().decode(encodedPayload));
    }
}
