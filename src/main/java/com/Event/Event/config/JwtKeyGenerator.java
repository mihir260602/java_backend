package com.Event.Event.config;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        // Generate a new key
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);

        // Print the key in Base64 format for use in your application
        System.out.println("Secret Key: " + java.util.Base64.getEncoder().encodeToString(key.getEncoded()));
    }
}
