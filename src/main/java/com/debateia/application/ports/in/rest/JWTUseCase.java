package com.debateia.application.ports.in.rest;

import com.debateia.domain.User;

public interface JWTUseCase {
    /**
     * Extracts username from JWT token
     * 
     * @param token JWT token string
     * @return extracted username
     */
    public String extractUsername(String token);

    /**
     * Generates a new JWT token for a user
     * 
     * @param user User object containing user details
     * @return generated JWT token string
     */
    public String generateToken(User user);

    /**
     * Generates a refresh token for a user
     * 
     * @param user User object containing user details
     * @return generated refresh token string
     */
    public String generateRefreshToken(User user);

    /**
     * Validates if a token is valid for a given user
     * 
     * @param token JWT token to validate
     * @return true if token is valid, false otherwise
     */
    public boolean isTokenValid(String token);
    
    public Integer extractUserId(String token);

}
