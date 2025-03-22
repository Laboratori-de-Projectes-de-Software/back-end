package com.example.back_end_eing.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.json.JSONFilter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "clasificacion")
public class Clasificacion {

    //******* COLUMNAS *******
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column
    private Integer puntuacionBot;
    @Column
    private Integer posicion;
    @Column
    private Integer victorias;
    @Column
    private Integer derrotas;
    @Column
    private Integer empates;



    //******* CONSTRUCTORES *******
    public Clasificacion() {}

    //******* RELACIONES CON OTRAS CLASES *******
    @ManyToOne
    @JoinColumn(
            name = "bot_id",
            nullable = false)

    private Bot bot;

    @ManyToOne
    @JoinColumn(
            name = "liga_id",
            nullable = false)

    private Liga liga;


}
