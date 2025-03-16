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
    @Column(unique=true, nullable = false)
    private String nombre;

    private Integer numVictorias;

    private Integer numEmpates;

    private Integer numDerrotas;

    private String cualidad;

    @ManyToOne
    private UsuarioEntity usuario;

    @OneToOne
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumVictorias() {
        return numVictorias;
    }

    public void setNumVictorias(Integer numVictorias) {
        this.numVictorias = numVictorias;
    }

    public Integer getNumEmpates() {
        return numEmpates;
    }

    public void setNumEmpates(Integer numEmpates) {
        this.numEmpates = numEmpates;
    }

    public Integer getNumDerrotas() {
        return numDerrotas;
    }

    public void setNumDerrotas(Integer numDerrotas) {
        this.numDerrotas = numDerrotas;
    }

    public String getCualidad() {
        return cualidad;
    }

    public void setCualidad(String cualidad) {
        this.cualidad = cualidad;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
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
}
