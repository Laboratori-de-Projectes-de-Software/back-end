package com.adondeband.back_end_adonde_band.JPA.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class UsuarioEntity {

    @Id
    @Column(nullable = false, unique = true, length = 36)
    private String nombre;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String contrasena;

    @OneToMany(mappedBy = "usuario")
    private List<BotEntity> bots;

    @ManyToOne
    private ImagenEntity imagen;

    @ManyToMany
    @JoinTable(
        name = "usuario_rol",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private List<RolEntity> roles;
}
