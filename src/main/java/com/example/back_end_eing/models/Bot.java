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
@Table(name = "bot")
public class Bot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombreBot;

    @Column
    private String descripcionBot;

    @Column
    private String fotoBot;

    @Column
    private Integer numVictorias;

    @Column
    private Integer numJornadas;

    @Column(unique = true, nullable = false)
    private String API_KEY;


    //******* CONSTRUCTORES *******
    public Bot() {}
    public Bot(String nombre, String descripcion, String foto, Integer victorias, Integer numJornadas, String API, Usuario user) {
        nombreBot = nombre;
        descripcionBot = descripcion;
        fotoBot = foto;
        numJornadas = victorias;
        this.numJornadas = numJornadas;
        API_KEY = API;
        usuario = user;
    }

    //******* RELACIONES CON OTRAS CLASES *******
    @ManyToOne
    @JoinColumn(
            name = "usuario_id",
            nullable = false)

    private Usuario usuario;

    @OneToMany(mappedBy = "bot", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Participacion> participaciones;

    @OneToMany(
            mappedBy = "bot",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )

    private Set<Clasificacion> clasificaciones = new HashSet<>();

}
