package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

public class UserDTORegisterResponse {

    private Long id;
    private String user;
    private String mail;

    // Constructor por defecto
    public UserDTORegisterResponse() {
    }

    // Constructor
    public UserDTORegisterResponse(Long id, String user, String mail) {
        this.id = id;
        this.user = user;
        this.mail = mail;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
