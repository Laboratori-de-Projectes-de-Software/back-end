package com.example.gironetaServer.infraestructure.adapters.out.db.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Enfrentamiento")
public class EnfrentamientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria autoincremental

    // Enum para el estado de la pelea
    public enum State {
        PENDING, IN_PROCESS, COMPLETED
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private State estado;

    @ManyToOne
    @JoinColumn(name = "bot_local", nullable = false)
    private BotEntity botLocal;

    @Column(nullable = false)
    private int puntuacionLocal;

    @ManyToOne
    @JoinColumn(name = "bot_visitante", nullable = false)
    private BotEntity botVisitante;

    @Column(nullable = false)
    private int puntuacionVisitante;

    @Column(nullable = false)
    private int ronda;

    @ManyToOne
    @JoinColumn(name = "jornada_id", nullable = false)
    private JornadaEntity jornada; // Relación con Jornada

    @OneToMany(mappedBy = "enfrentamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MensajeEntity> mensajes; // Relación con Mensajes

}
