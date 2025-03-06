package com.adondeband.back_end_adonde_band.JPA.entities;

import com.adondeband.back_end_adonde_band.api.dominio.modelos.Bot;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Usuario {

    @Id
    @Column(nullable = false, unique = true, length = 36)
    private String nombre;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String contrasena;

    @OneToMany(mappedBy = "usuario")
    private List<Bot> bots;

    @OneToOne
    private Imagen imagen;

    @ManyToMany
    @JoinTable(
        name = "usuario_rol",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private List<Rol> roles;
}
