package com.example.gironetaServer.infraestructure.adapters.out.db.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Mensaje")
public class MensajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime hora = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "bot_id", nullable = false)
    private BotEntity bot;

    @ManyToOne
    @JoinColumn(name = "enfrentamiento_id", nullable = false)
    private EnfrentamientoEntity enfrentamiento;

    protected MensajeEntity() {
    }

    public MensajeEntity(String contenido, EnfrentamientoEntity enfrentamiento) {
        this.contenido = contenido;
        this.enfrentamiento = enfrentamiento;
    }
}
