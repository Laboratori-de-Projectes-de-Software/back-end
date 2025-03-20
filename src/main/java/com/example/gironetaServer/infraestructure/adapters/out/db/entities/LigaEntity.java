package com.example.gironetaServer.infraestructure.adapters.out.db.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Liga", uniqueConstraints = {
        @UniqueConstraint(columnNames = "nombre") // El nombre de la liga debe ser único
})
public class LigaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria auto-generada

    @Column(nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @Column(nullable = false)
    private int duracionEnfrentamientos;

    @OneToMany(mappedBy = "liga", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JornadaEntity> jornadas; // Relación con Jornada

    protected LigaEntity() {
    }

    public LigaEntity(String nombre, Date fechaCreacion, Date fechaFin, int duracionEnfrentamientos) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaFin = fechaFin;
        this.duracionEnfrentamientos = duracionEnfrentamientos;
    }
}
