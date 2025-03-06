package com.adondeband.back_end_adonde_band.JPA.entities;

import com.adondeband.back_end_adonde_band.dominio.modelos.Liga;
import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Setter
public class LigaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    private ESTADO estado;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    @ManyToOne
    private ImagenEntity imagen;

    @OneToMany(mappedBy = "liga")
    private List<ParticipacionEntity> participaciones;

    /*
    public LigaEntity map(Liga liga) {
        LigaEntity ligaEntity = new LigaEntity();
        ligaEntity.setId(liga.getId());
        ligaEntity.setNombre(liga.getNombre());
        ligaEntity.setEstado(ESTADO.valueOf(liga.getEstado().name()));
        ligaEntity.setFechaInicio(liga.getFechaInicio());
        ligaEntity.setFechaFin(liga.getFechaFin());
        imagen = null;
        participaciones = null;
        return ligaEntity;
    }
     */
}
