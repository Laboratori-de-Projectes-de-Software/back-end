package com.debateia.JWT_viejo;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

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
                .getSubject();
    }

    /*
    public String generateToken( UserDto user) {
        return buildToken(user, refreshExpiration);
    }

    public String generateRefreshToken(UserDto user) {
        return buildToken(user, refreshExpiration);
    }


    private String buildToken( UserDto user, final long expiration) {
        
        return Jwts
                .builder()
                .claims(Map.of("name", user.getName()))
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }



    public boolean isTokenValid(String token, User user) {
        final String username = extractUsername(token);
        return (username.equals(user.getEmail())) && !isTokenExpired(token);
    }
*/
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
