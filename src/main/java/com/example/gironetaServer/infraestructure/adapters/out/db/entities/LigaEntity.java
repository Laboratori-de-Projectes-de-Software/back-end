package com.example.gironetaServer.infraestructure.adapters.out.db.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "duracion_enfrentamientos", nullable = false)
    private Long matchTime;

    @Column(name = "nombre", nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Integer rounds;

    @Column(nullable = false, length = 255)
    private String urlImagen;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity usuario; // Relación con Usuario

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "liga_bot", joinColumns = @JoinColumn(name = "liga_id"), inverseJoinColumns = @JoinColumn(name = "bot_id"))
    private Set<BotEntity> bots; // Relación con Bots

    @OneToMany(mappedBy = "liga", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JornadaEntity> jornadas; // Relación con Jornadas

    public LigaEntity() {
    }

    public LigaEntity(String name, String urlImagen, Integer rounds, Long matchTime, Set<BotEntity> bots,
            UserEntity usuario,
            Set<JornadaEntity> jornadas) {
        this.name = name;
        this.urlImagen = urlImagen;
        this.rounds = rounds;
        this.matchTime = matchTime;
        this.fechaCreacion = LocalDateTime.now();
        this.bots = bots;
        this.usuario = usuario;
        this.jornadas = jornadas;
    }

}
