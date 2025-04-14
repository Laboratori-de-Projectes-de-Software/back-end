package com.adondeband.back_end_adonde_band.jpa.enfrentamiento;

import com.adondeband.back_end_adonde_band.jpa.bot.BotEntity;
import com.adondeband.back_end_adonde_band.jpa.conversacion.ConversacionEntity;
import com.adondeband.back_end_adonde_band.jpa.entities.ESTADO_Entity;
import com.adondeband.back_end_adonde_band.jpa.jornada.JornadaEntity;
import com.adondeband.back_end_adonde_band.jpa.entities.RESULTADO_Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class EnfrentamientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
