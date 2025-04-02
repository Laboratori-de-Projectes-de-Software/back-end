package com.example.gironetaServer.infraestructure.adapters.out.db.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
@Entity
@Table(name = "Bot")
public class BotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria autoincremental

    @Column(nullable = false, length = 100)
    private String name;

    //prompt
    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(nullable = false, length = 255)
    private String urlImagen;

    //url de datos
    @Column(nullable = false, length = 255)
    private String endpoint;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private UserEntity usuario; // Relación con Usuario

    public BotEntity (){

    }

    public BotEntity(Long id, String name, String descripcion, String urlImagen, String endpoint) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.endpoint = endpoint;
    }

    public BotEntity(Long id, String name, String descripcion, String urlImagen, String endpoint, UserEntity usuario) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.endpoint = endpoint;
        this.usuario = usuario;
    }

}
