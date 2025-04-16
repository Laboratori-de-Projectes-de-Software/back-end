package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

public class UserDTORegister {
    private String user;
    private String mail;
    private String password;

    // Constructor por defecto
    public UserDTORegister() {
    }

    // Constructor
    public UserDTORegister(String user, String mail, String password) {
        this.user = user;
        this.mail = mail;
        this.password = password;
    }

    // Getters y setters
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String email) {
        this.mail = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}