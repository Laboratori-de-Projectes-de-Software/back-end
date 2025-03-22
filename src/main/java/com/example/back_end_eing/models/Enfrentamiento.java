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
public class Enfrentamiento {

    //******* COLUMNAS *******
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    //FALTA AÑADIR MÉTOD PARA CODIFICAR Y DECODE JSON
    @Lob //almacenar grandes cantidades de texto
    @Column(columnDefinition = "TEXT") //Guarda el JSON en formato texto
    private String clasificacion;

    @Column
    private String resultado;

    //******* CONSTRUCTORES *******
    public Enfrentamiento() {}

    //******* RELACIONES CON OTRAS CLASES *******
    @OneToMany(mappedBy = "enfrentamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Participacion> participaciones;


    @ManyToOne
    @JoinColumn(
            name = "jornada_id",
            nullable = false)
    private Jornada jornada;
}
