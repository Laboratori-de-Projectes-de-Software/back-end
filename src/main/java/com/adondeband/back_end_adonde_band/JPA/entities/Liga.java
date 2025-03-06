package com.adondeband.back_end_adonde_band.JPA.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class Liga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    private ESTADO estado;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    @OneToMany(mappedBy = "liga")
    private List<Participacion> participaciones;
}
