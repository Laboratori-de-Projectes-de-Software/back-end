package com.adondeband.back_end_adonde_band.dominio.modelos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Bot {

    // Getter for nombre
    private String nombre;

    private Integer numVictorias;

    private Integer numEmpates;

    private Integer numDerrotas;

    private final String cualidad;

    private Usuario usuario;

    private Imagen imagen;

    private List<Participacion> participaciones;

    private List<Enfrentamiento> enfrentamientos;

    public Bot (String nombre, String cualidad) {
        this.nombre = nombre;
        this.cualidad = cualidad;
        this.numVictorias = 0;
        this.numEmpates = 0;
        this.numDerrotas = 0;
    }
}
