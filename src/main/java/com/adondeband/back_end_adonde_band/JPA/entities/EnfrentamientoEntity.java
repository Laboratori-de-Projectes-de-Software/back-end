package com.adondeband.back_end_adonde_band.JPA.entities;

import jakarta.persistence.*;

@Entity
@Table
public class EnfrentamientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private ESTADO estado;
    private boolean ganador;

    @ManyToOne
    private BotEntity bot1;

    @ManyToOne
    private BotEntity bot2;

    @OneToOne
    private ConversacionEntity conversacion;

    @ManyToOne
    private JornadaEntity jornada;

    BotEntity getGanador() {
        return ganador ? bot1 : bot2;
    }
}
