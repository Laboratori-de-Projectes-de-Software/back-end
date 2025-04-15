package com.adondeband.back_end_adonde_band.jpa.bot;

import com.adondeband.back_end_adonde_band.jpa.imagen.ImagenEntity;
import com.adondeband.back_end_adonde_band.jpa.participacion.ParticipacionEntity;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
public class BotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable = false)
    private String nombre;

    private Integer numVictorias;

    private Integer numEmpates;

    private Integer numDerrotas;

    private String cualidad;

    private String endpoint;

    @ManyToOne
    private UsuarioEntity usuario;

    @ManyToOne
    private ImagenEntity imagen;

    @OneToMany(mappedBy = "bot")
    private List<ParticipacionEntity> participaciones;


    public BotEntity(String nombre, String cualidad) {
        this.nombre = nombre;
        this.cualidad = cualidad;
        this.numVictorias = 0;
        this.numEmpates = 0;
        this.numDerrotas = 0;
    }

    public BotEntity() {
    }
}
