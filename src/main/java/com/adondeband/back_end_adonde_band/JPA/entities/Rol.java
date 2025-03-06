package com.adondeband.back_end_adonde_band.JPA.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Rol {
    @Id
    @Column(nullable = false, unique = true, length = 36)
    private String nombre;
    private String descripcion;

    @ManyToMany
    private List<Usuario> usuarios;
}
