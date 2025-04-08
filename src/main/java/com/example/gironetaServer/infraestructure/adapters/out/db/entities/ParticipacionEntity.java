package com.example.gironetaServer.infraestructure.adapters.out.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int puntuacion;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int numGanados;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int numEmpatados;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int numPerdidos;

    // Constructor sin parámetros
    public ParticipacionEntity() {
    }

    // Constructor con parámetros
    public ParticipacionEntity(BotEntity bot, LeagueEntity liga, int puntuacion, int numGanados, int numEmpatados,
            int numPerdidos) {
        this.bot = bot;
        this.liga = liga;
        this.puntuacion = puntuacion;
        this.numGanados = numGanados;
        this.numEmpatados = numEmpatados;
        this.numPerdidos = numPerdidos;
    }
}
