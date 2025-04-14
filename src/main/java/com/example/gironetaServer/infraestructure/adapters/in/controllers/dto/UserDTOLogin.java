package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

public class UserDTOLogin {

    private String email;
    private String password;

    // Constructor por defecto
    public UserDTOLogin() {
    }

    // Constructor
    public UserDTOLogin(String email, String password) {
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
