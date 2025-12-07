package com.fresco.tenderManagement.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

public class JWTUtil {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // 5 hours
    private static final Logger log = LoggerFactory.getLogger(JWTUtil.class);
    private final String secret = "randomkey123randomkey123randomkey123randomkey123randomkey123randomkey123";


   // private final Key secreteKey = Keys.hmacShaKeyFor(secret.getBytes());


    public String generateToken(UserDetails userDetails) {
          return null;

    }

    public String getUsernameFromToken(String token) {
        return null;

    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        return null;

    }
}
