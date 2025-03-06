package com.adondeband.back_end_adonde_band.api.dominio.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Participacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "bot_id")
    private Bot bot;

    @ManyToOne
    @JoinColumn(name = "liga_id")
    private Liga liga;

    private int posicion;
    private int puntuacion;
}
