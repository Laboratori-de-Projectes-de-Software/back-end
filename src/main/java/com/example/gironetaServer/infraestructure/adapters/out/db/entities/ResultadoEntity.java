package com.example.gironetaServer.infraestructure.adapters.out.db.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Resultado")
public class ResultadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Se puede omitir si la clave primaria es compuesta

    @Column(nullable = false)
    private int puntos;

    @ManyToOne
    @JoinColumn(name = "bot_id", nullable = false)
    private BotEntity bot;

    @ManyToOne
    @JoinColumn(name = "enfrentamiento_id", nullable = false)
    private EnfrentamientoEntity enfrentamiento;

    protected ResultadoEntity() {
    }

    public ResultadoEntity(int puntos, BotEntity bot, EnfrentamientoEntity enfrentamiento) {
        this.puntos = puntos;
        this.bot = bot;
        this.enfrentamiento = enfrentamiento;
    }
}
