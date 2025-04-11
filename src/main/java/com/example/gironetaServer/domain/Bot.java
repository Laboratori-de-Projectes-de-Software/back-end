package com.example.gironetaServer.domain;

public class Bot {
    private Long id; //El consenso usa INT no LONG
    private String name;
    private String descripcion;
    private String urlImagen;
    private String endpoint;
    private Long usuario_id;

    public Bot() {
    }

    public Bot(Long id, String name, String descripcion, String urlImagen, String endpoint, Long usuario_id) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.endpoint = endpoint;
        this.usuario_id = usuario_id;
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

    public Long getUsuario_id() {
        return usuario_id;
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

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }
}
