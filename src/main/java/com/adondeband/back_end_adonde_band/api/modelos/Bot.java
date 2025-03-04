package com.adondeband.back_end_adonde_band.api.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
public class Bot {

    @Id
    @Column(unique=true, nullable = false)
    private String nombre;

    private Integer numVictorias;

    private Integer numEmpates;

    private Integer numDerrotas;

    private String defensa;

    @ManyToOne
    private Usuario usuario;

    @ManyToMany(mappedBy = "bots")
    private List<Liga> ligas;

    @OneToMany
    private List<Conversacion> conversaciones;

    public Bot (String nombre, String defensa) {
        this.nombre = nombre;
        this.defensa = defensa;
        this.numVictorias = 0;
        this.numEmpates = 0;
        this.numDerrotas = 0;
    }
}
