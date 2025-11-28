package com.fresco.tenderManagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // 5 hours

    // Key must be at least 512 bits (64 bytes) for HS512
    private final String secret = "randomkey123randomkey123randomkey123randomkey123randomkey123randomkey123";
    private final Key secretKey = Keys.hmacShaKeyFor(secret.getBytes());

    public String generateToken(UserDetails userDetails) {
       // Map<String, Object> claims = new HashMap<>();
        //claims.put("roles", userDetails.getAuthorities());

        return Jwts.builder()
                //.setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        final Date expiration = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return (username.equals(userDetails.getUsername()) && !expiration.before(new Date()));
    }
}
