package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

import com.example.gironetaServer.infraestructure.adapters.in.controllers.AuthenticationController;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserResponseDTO {
    private String token;
    private LocalDateTime expiresIn;
    private String username;
    private Long id;

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

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
