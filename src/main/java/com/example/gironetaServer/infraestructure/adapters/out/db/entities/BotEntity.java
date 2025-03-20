package com.example.gironetaServer.infraestructure.adapters.out.db.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Bot")
public class BotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria autoincremental

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String ideologia;

    @ManyToOne
    @JoinColumn(name = "usuario_correo", nullable = true)
    private UserEntity usuario; // Relación con Usuario

    protected BotEntity() {
    }

    public BotEntity(String nombre, String ideologia, UserEntity usuario) {
        this.nombre = nombre;
        this.ideologia = ideologia;
        this.usuario = usuario;
    }
}
