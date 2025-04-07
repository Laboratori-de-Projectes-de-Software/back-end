package com.example.gironetaServer.infraestructure.adapters.out.db.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Liga", uniqueConstraints = {
        @UniqueConstraint(columnNames = "nombre") // El nombre de la liga debe ser único
})
public class LeagueEntity {

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "liga_bot", joinColumns = @JoinColumn(name = "liga_id"), inverseJoinColumns = @JoinColumn(name = "bot_id"))
    private Set<BotEntity> bots; // Relación con Bots

    @OneToMany(mappedBy = "liga", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JornadaEntity> jornadas; // Relación con Jornadas

    public enum State {
        Created,
        Started,
        Finished
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private State state = State.Created; // Estado de la liga

    public LeagueEntity() {
    }

    public LeagueEntity(String name, String urlImagen, Integer rounds, Long matchTime, Set<BotEntity> bots,
                        UserEntity usuario, Set<JornadaEntity> jornadas) {
        this.name = name;
        this.urlImagen = urlImagen;
        this.rounds = rounds;
        this.matchTime = matchTime;
        this.fechaCreacion = LocalDateTime.now();
        this.bots = bots;
        this.usuario = usuario;
        this.jornadas = jornadas;
    }

    // Métodos getter
    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Long getMatchTime() {
        return matchTime;
    }

    public String getName() {
        return name;
    }

    public Integer getRounds() {
        return rounds;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public Set<BotEntity> getBots() {
        return bots;
    }

    public Set<JornadaEntity> getJornadas() {
        return jornadas;
    }

    // Métodos setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setMatchTime(Long matchTime) {
        this.matchTime = matchTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public void setBots(Set<BotEntity> bots) {
        this.bots = bots;
    }

    public void setJornadas(Set<JornadaEntity> jornadas) {
        this.jornadas = jornadas;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
