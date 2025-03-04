package com.adondeband.back_end_adonde_band.api.modelos;


import jakarta.persistence.*;

@Entity
@Table
public class Conversacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ficheroRuta;

    @OneToOne
    private Enfrentamiento enfrentamiento;
}
