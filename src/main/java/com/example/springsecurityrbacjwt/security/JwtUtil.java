package com.example.springsecurityrbacjwt.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.Set;

import static com.example.springsecurityrbacjwt.security.SecurityConstants.*;

public class JwtUtil {

    public static String createToken(final String username, final Set<String> roles, final Set<String> privileges) {
        long now = System.currentTimeMillis();
        return PREFIX + Jwts.builder()
                .claim(USERNAME, username)
                .claim(ROLES, roles)
                .claim(PRIVILEGES, privileges)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + EXPIRATION_TIME * 1000))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS512)
                .compact();
    }
}
