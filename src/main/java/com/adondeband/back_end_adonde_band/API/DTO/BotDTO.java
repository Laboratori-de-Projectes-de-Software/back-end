package com.adondeband.back_end_adonde_band.API.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class BotDTO {

    private String nombre;
    private String cualidad;
    private String UsuarioDTO;
    private Integer numVictorias;
    private Integer numEmpates;
    private Integer numDerrotas;

    public BotDTO(String nombre, String cualidad) {
        this.nombre = nombre;
        this.cualidad = cualidad;
        this.numVictorias = 0;
        this.numEmpates = 0;
        this.numDerrotas = 0;
        this.UsuarioDTO = null;
    }
}
