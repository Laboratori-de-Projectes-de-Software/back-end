package com.debateia.application.service;

import com.debateia.application.ports.in.rest.JWTUseCase;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.debateia.application.ports.out.persistence.UserRepository;
import com.debateia.domain.User;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService implements JWTUseCase {

    private final UserRepository repository;

    @Value("${spring.application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${spring.application.security.jwt.expiration}")
    private long jwtExpiration;

    @Value("${spring.application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("username", String.class); // ← Accedemos al claim personalizado "username"
    }

    @Override
    public Integer extractUserId(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("userId", Integer.class);
    }
    

    public String generateToken(User user) {
        return buildToken(user, refreshExpiration);
    }

    public String generateRefreshToken(User user) {
        return buildToken(user, refreshExpiration);
    }

    private String buildToken(User user, final long expiration) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("userId", user.getUserId()); // 👈 Añadimos el ID del usuario
        return Jwts
                .builder()
                .claims(claims)
                .subject(user.getMail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String authentication) {
        System.out.println("AUTHENTICATTIENEJEJNE: " + authentication);
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String authToken = authentication.substring(7);
        final String currentEmail = this.extractUsername(authToken);
        if (currentEmail == null) {
            return false;
        }
        User user = this.repository.findByUsername(currentEmail).orElseThrow();
        return (currentEmail.equals(user.getUsername())) && !isTokenExpired(authToken);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

    private SecretKey getSignInKey() {
        final byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
