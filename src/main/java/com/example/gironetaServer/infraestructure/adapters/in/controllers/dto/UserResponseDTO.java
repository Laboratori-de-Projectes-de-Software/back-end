package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

import com.example.gironetaServer.infraestructure.adapters.in.controllers.AuthenticationController;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserResponseDTO {
    private String token;
    private LocalDateTime expiresIn;
    private String user;
    private Long userId;

    public UserResponseDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(LocalDateTime expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
