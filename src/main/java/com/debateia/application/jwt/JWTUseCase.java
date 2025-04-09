package com.debateia.application.jwt;

import com.debateia.adapter.out.persistence.UserEntity;

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
    public String generateToken(UserEntity user);

    /**
     * Generates a refresh token for a user
     * 
     * @param user User object containing user details
     * @return generated refresh token string
     */
    public String generateRefreshToken(UserEntity user);

    /**
     * Validates if a token is valid for a given user
     * 
     * @param token JWT token to validate
     * @param user  User to validate against
     * @return true if token is valid, false otherwise
     */
    public boolean isTokenValid(String token, UserEntity user);

}
