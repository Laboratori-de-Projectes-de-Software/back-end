package com.debateia.domain;

public class UsuarioDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getId() {
        return id;
    }
    public UsuarioDto(){

    }
    public UsuarioDto(String name, String email,String password) {
        this.name = name;
        this.email = email;
        this.password=password;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
