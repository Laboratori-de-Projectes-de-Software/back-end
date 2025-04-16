package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

public class UserDTOLogin {

    private String user;
    private String password;

    // Constructor por defecto
    public UserDTOLogin() {
    }

    // Constructor
    public UserDTOLogin(String user, String password) {
        this.user = user;
        this.password = password;
    }

    // Getters y setters
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
