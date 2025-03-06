package com.adondeband.back_end_adonde_band.JPA.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ruta;

}
