package com.adondeband.back_end_adonde_band.JPA.entities;

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
    private UsuarioEntity usuario;

    @ManyToOne
    private ImagenEntity imagen;

    @OneToMany(mappedBy = "bot")
    private List<ParticipacionEntity> participaciones;

    @OneToMany(mappedBy = "bot")
    private List<EnfrentamientoEntity> enfrentamientos;

    public BotEntity(String nombre, String cualidad) {
        this.nombre = nombre;
        this.cualidad = cualidad;
        this.numVictorias = 0;
        this.numEmpates = 0;
        this.numDerrotas = 0;
    }
}
