package com.example.gironetaServer.infraestructure.adapters.out.db.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Participacion")
public class ParticipacionEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "bot_id", referencedColumnName = "id")
    private BotEntity bot;

    @Id
    @ManyToOne
    @JoinColumn(name = "liga_id", referencedColumnName = "id")
    private LeagueEntity liga;

    @Column(nullable = false)
    private int puntuacion;

    @Column(nullable = false)
    private int numGanados;

    @Column(nullable = false)
    private int numEmpatados;

    @Column(nullable = false)
    private int numPerdidos;

    // Constructor sin parámetros
    public ParticipacionEntity() {
    }

    // Constructor con parámetros
    public ParticipacionEntity(BotEntity bot, LeagueEntity liga, int puntuacion, int numGanados, int numEmpatados, int numPerdidos) {
        this.bot = bot;
        this.liga = liga;
        this.puntuacion = puntuacion;
        this.numGanados = numGanados;
        this.numEmpatados = numEmpatados;
        this.numPerdidos = numPerdidos;
    }
}
