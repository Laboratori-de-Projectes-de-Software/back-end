package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

public class LoginUserDto {

    private String email;

    private String password;

    // Constructor por defecto
    public LoginUserDto() {
    }

    // Constructor
    public LoginUserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters y setters
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
