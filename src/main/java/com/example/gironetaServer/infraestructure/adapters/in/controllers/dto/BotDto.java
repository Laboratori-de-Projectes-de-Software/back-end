package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

public class BotDto {
    private String name;
    private String descripcion;
    private String urlImagen;
    private String endpoint;

    public BotDto() {
    }

    public BotDto(String name, String descripcion, String urlImagen, String endpoint) {
        this.name = name;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.endpoint = endpoint;
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
