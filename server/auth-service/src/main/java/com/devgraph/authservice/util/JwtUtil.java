package com.devgraph.authservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    // This grabs the secret key from our properties file
    @Value("${jwt.secret}")
    private String secret;
    // A token expires after a certain amount of time (e.g., 24 hours)
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    // This converts our string secret into a cryptographic key
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    // This is the function that actually builds the VIP pass
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // The user's identity
                .setIssuedAt(new Date()) // When it was created
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // When it expires
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // The cryptographic signature
                .compact();
    }
}
