package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BotDto {
    private Long id;
    private String name;
    private String descripcion;
    private String urlImagen;
    private String endpoint;

    public BotDto(){

    }

    public BotDto(Long id, String name, String descripcion, String urlImagen, String endpoint) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.endpoint = endpoint;
    }
}
