package com.example.gironetaServer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Bot {
    private Long id;
    private String name;
    private String descripcion;
    private String urlImagen;
    private String endpoint;
    private String usuario_correo;

    public Bot() {
    }

    public Bot(Long id, String name, String descripcion, String urlImagen, String endpoint, String usuario_correo) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.endpoint = endpoint;
        this.usuario_correo = usuario_correo;
    }

}
