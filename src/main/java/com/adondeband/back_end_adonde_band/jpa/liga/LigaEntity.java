package com.adondeband.back_end_adonde_band.jpa.liga;

import com.adondeband.back_end_adonde_band.jpa.entities.ESTADO_Entity;
import com.adondeband.back_end_adonde_band.jpa.imagen.ImagenEntity;
import com.adondeband.back_end_adonde_band.jpa.participacion.ParticipacionEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class LigaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    private ESTADO_Entity estado;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    @ManyToOne
    private ImagenEntity imagen;

    @OneToMany(mappedBy = "liga")
    private List<ParticipacionEntity> participaciones;
}
