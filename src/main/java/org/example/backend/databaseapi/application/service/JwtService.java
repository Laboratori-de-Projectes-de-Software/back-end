package org.example.backend.databaseapi.application.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.backend.databaseapi.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret:defaultSecretKey12345678901234567890}")
    private String secretKey;

    @Value("${jwt.expiration:86400000}") // 24 horas
    private long jwtExpiration;

    private SecretKey getSignInKey() {
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Usuario usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", usuario.getUserId());
        claims.put("email", usuario.getEmail());
        claims.put("nombre", usuario.getNombre());

        return Jwts.builder()
                .claims(claims)
                .subject(usuario.getEmail().value())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey())
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, Usuario usuario) {
        final String username = extractUsername(token);
        return (username.equals(usuario.getEmail().value()) && !isTokenExpired(token));
    }
}