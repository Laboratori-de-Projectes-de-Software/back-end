package com.adondeband.back_end_adonde_band.JPA.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class EnfrentamientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private ESTADO_Entity estado;
    private RESULTADO_Entity resultado;

    @ManyToOne
    private BotEntity local;

    @ManyToOne
    private BotEntity visitante;

    @OneToOne
    private ConversacionEntity conversacion;

    @ManyToOne
    private JornadaEntity jornada;
}
