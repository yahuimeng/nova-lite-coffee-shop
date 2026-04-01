package com.nova.coffee.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * JWT 令牌服务。
 */
@Component
public class JwtTokenService {

    private final SecretKey key;
    private final long expireSeconds;

    public JwtTokenService(
        @Value("${app.security.jwt-secret}") String secret,
        @Value("${app.security.jwt-expire-seconds}") long expireSeconds
    ) {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        this.key = Keys.hmacShaKeyFor(secretBytes.length >= 32
            ? secretBytes
            : Decoders.BASE64.decode("Y29mZmVlLXNob3AtZGVtby1zZWNyZXQta2V5LTEyMzQ1Njc4OTBhYmNkZWY="));
        this.expireSeconds = expireSeconds;
    }

    public String generateToken(String username, String role) {
        Instant now = Instant.now();
        return Jwts.builder()
            .subject(username)
            .claims(Map.of("role", role))
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plusSeconds(expireSeconds)))
            .signWith(key)
            .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }
}
