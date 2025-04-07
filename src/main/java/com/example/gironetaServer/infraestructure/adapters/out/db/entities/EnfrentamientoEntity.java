package com.example.gironetaServer.infraestructure.adapters.out.db.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Enfrentamiento")
public class EnfrentamientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria autoincremental

    public enum Estado {
        Created,
        Started,
        Finished
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "jornada_id", nullable = false)
    private JornadaEntity jornada; // Relación con Jornada

    @OneToMany(mappedBy = "enfrentamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MensajeEntity> mensajes; // Relación con Mensajes

    @OneToMany(mappedBy = "enfrentamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ResultadoEntity> resultados; // Relación con Resultado

    public EnfrentamientoEntity() {
    }

    public EnfrentamientoEntity(Estado estado, JornadaEntity jornada) {
        this.estado = estado;
        this.jornada = jornada;
    }
}
