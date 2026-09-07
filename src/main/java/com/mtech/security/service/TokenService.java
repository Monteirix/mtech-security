package com.mtech.security.service;

import com.mtech.security.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generetadeToken(User user){
        Instant now = Instant.now();
        Instant expiration =  now.plusSeconds(60 * 60);

        return Jwts.builder().subject(user.getUsername())
                .issuedAt(java.util.Date.from(now))
                .expiration(java.util.Date.from(expiration))
                .signWith(getSigningKey())
                .compact();

    }
}
