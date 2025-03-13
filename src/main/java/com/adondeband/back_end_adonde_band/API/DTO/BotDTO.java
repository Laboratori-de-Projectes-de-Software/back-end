package com.adondeband.back_end_adonde_band.API.DTO;

import com.adondeband.back_end_adonde_band.dominio.Ids.UsuarioId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class BotDTO {

    private String nombre;

    private Integer numVictorias;

    private Integer numEmpates;

    private Integer numDerrotas;

    private String cualidad;

    private UsuarioId usuario;

    public BotDTO(String nombre, String cualidad) {
        this.nombre = nombre;
        this.cualidad = cualidad;
        this.numVictorias = 0;
        this.numEmpates = 0;
        this.numDerrotas = 0;
        this.usuario = null;
    }
}
