package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

public class RegisterRequestDto {
    private String username;
    private String email;
    private String password;

    // Constructor por defecto
    public RegisterRequestDto() {
    }

    // Constructor
    public RegisterRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}