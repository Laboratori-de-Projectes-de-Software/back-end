package com.adondeband.back_end_adonde_band.JPA.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Jornada {

    @Id
    @Column(unique=true, nullable = false)
    private int numJornada;

    @ManyToOne
    private Liga liga;

    @OneToMany(mappedBy = "jornada")
    private List<Enfrentamiento> enfrentamiento;
}
