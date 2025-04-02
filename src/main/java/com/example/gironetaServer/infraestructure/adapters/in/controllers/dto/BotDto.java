package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

public class BotDto {
    private Long id;
    private String name;
    private String descripcion;
    private String urlImagen;
    private String endpoint;

    public BotDto() {
    }

    public BotDto(Long id, String name, String descripcion, String urlImagen, String endpoint) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.endpoint = endpoint;
    }

    // Métodos getter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public String getEndpoint() {
        return endpoint;
    }

    // Métodos setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
