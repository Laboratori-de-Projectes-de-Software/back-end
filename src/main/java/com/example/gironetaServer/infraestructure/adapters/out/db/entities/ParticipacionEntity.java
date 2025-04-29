package com.example.gironetaServer.infraestructure.adapters.out.db.entities;

import com.example.gironetaServer.infraestructure.adapters.ParticipationId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@Entity
@IdClass(ParticipationId.class)
@Table(name = "Participacion")
public class ParticipacionEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "bot_id", referencedColumnName = "id")
    private BotEntity bot;

    @Id
    @ManyToOne
    @JoinColumn(name = "liga_id", referencedColumnName = "id")
    private LeagueEntity league;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int puntuacion;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int numGanados;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int numEmpatados;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int numPerdidos;

    public ParticipacionEntity() {
    }

    public ParticipacionEntity(BotEntity bot, LeagueEntity liga, int puntuacion, int numGanados, int numEmpatados, int numPerdidos) {
        this.bot = bot;
        this.league = liga;
        this.puntuacion = puntuacion;
        this.numGanados = numGanados;
        this.numEmpatados = numEmpatados;
        this.numPerdidos = numPerdidos;
    }



}




