package com.adondeband.back_end_adonde_band.JPA.entities;


import jakarta.persistence.*;

@Entity
@Table
public class ConversacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ficheroRuta;

    @OneToOne
    private EnfrentamientoEntity enfrentamiento;
}
