package com.adondeband.back_end_adonde_band.JPA.entities;

import com.adondeband.back_end_adonde_band.api.dominio.modelos.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter
public class BotEntity {

    @Id
    @Column(unique=true, nullable = false)
    private String nombre;

    private Integer numVictorias;

    private Integer numEmpates;

    private Integer numDerrotas;

    private String cualidad;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "bot")
    private List<Participacion> participaciones;

    @OneToMany(mappedBy = "bot")
    private List<Enfrentamiento> enfrentamientos;

    public BotEntity(String nombre, String cualidad) {
        this.nombre = nombre;
        this.cualidad = cualidad;
        this.numVictorias = 0;
        this.numEmpates = 0;
        this.numDerrotas = 0;
    }
}
