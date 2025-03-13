package com.adondeband.back_end_adonde_band.JPA.entities;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ESTADO_Entity getEstado() {
        return estado;
    }

    public void setEstado(ESTADO_Entity estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ImagenEntity getImagen() {
        return imagen;
    }

    public void setImagen(ImagenEntity imagen) {
        this.imagen = imagen;
    }

    public List<ParticipacionEntity> getParticipaciones() {
        return participaciones;
    }

    public void setParticipaciones(List<ParticipacionEntity> participaciones) {
        this.participaciones = participaciones;
    }

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
