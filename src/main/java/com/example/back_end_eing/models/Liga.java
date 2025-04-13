package com.example.back_end_eing.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
public class Liga {

    //******* COLUMNAS *******
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column
    private String nombreLiga;

    @Column
    private Integer numJornadas;

    @Column
    private Integer numBots;

    @Column
    private String estado;

    @Column
    private String imagen;

    @Column
    private Integer jornadaActual;

    @Column
    private Long matchTime;

    //******* CONSTRUCTOR *******
    public Liga() {}
    public Liga(String nombreLiga, Integer numJornadas, Integer numBots, String estado, String imagen, Integer jornadaActual, Usuario usuario, Long matchTime) {
        this.nombreLiga = nombreLiga;
        this.numJornadas = numJornadas;
        this.numBots = numBots;
        this.estado = estado;
        this.imagen = imagen;
        this.jornadaActual = jornadaActual;
        this.usuario = usuario;
        this.matchTime = matchTime;
    }

    //******* RELACIONES CON OTRAS CLASES *******
    @OneToMany(
            mappedBy = "liga",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private Set<Jornada> jornadas = new HashSet<>();

    @OneToMany(
            mappedBy = "liga",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private Set<Clasificacion> clasificacions = new HashSet<>();

    @ManyToOne
    @JoinColumn(
            name = "usuario_id",
            nullable = false)

    private Usuario usuario;
}
