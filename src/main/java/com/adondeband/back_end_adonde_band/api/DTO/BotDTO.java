package com.adondeband.back_end_adonde_band.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BotDTO {

    private String nombre;
    private String defensa;
    private String usuario;
    private Integer numVictorias;
    private Integer numEmpates;
    private Integer numDerrotas;

    public BotDTO(String nombre, String defensa) {
        this.nombre = nombre;
        this.defensa = defensa;
        this.numVictorias = 0;
        this.numEmpates = 0;
        this.numDerrotas = 0;
    }
}
