package com.example.gironetaServer.infraestructure.adapters.in.controllers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BotSummaryDto {
    private Long id;
    private String name;
    private String urlImagen;
    private String usuario_correo;

    public BotSummaryDto(){

    }

    public BotSummaryDto(Long id, String name, String urlImagen, String usuario_correo) {
        this.id = id;
        this.name = name;
        this.urlImagen = urlImagen;
        this.usuario_correo = usuario_correo;
    }
}
