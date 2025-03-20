package com.example.gironetaServer.infraestructure.adapters.out.db.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Jornada")
public class JornadaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria autoincremental

    @Column(nullable = false)
    private int numJornada; // Número de jornada

    @ManyToOne
    @JoinColumn(name = "liga_id", nullable = false)
    private LigaEntity liga; // Relación ManyToOne con Liga

    @OneToMany(mappedBy = "jornada", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EnfrentamientoEntity> enfrentamientos; // Relación con Enfrentamiento

    protected JornadaEntity() {
    }

    public JornadaEntity(int numJornada, LigaEntity liga) {
        this.numJornada = numJornada;
        this.liga = liga;
    }
}
