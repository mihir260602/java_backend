package com.Event.Event.config;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtUtils {

    private String secretKey = "58th9uerngt98e4ht0erierw0gfn89erwhyt0ern9gher80gthrehg809hh0er8h00h04htr0834ht0"; // Replace with your actual key

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours validity
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()) // Ensure secret key is properly encoded
                .compact();
    }
}
