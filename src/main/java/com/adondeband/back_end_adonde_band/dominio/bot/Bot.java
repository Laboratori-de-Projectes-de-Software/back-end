package com.adondeband.back_end_adonde_band.dominio.bot;

import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionId;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Bot {

    private BotId nombre;

    private Integer numVictorias;

    private Integer numEmpates;

    private Integer numDerrotas;

    private final String cualidad;

    private UsuarioId usuario;

    private Imagen imagen;

    private List<ParticipacionId> participaciones;

    public Bot (BotId nombre, String cualidad) {
        this.nombre = nombre;
        this.cualidad = cualidad;
        this.numVictorias = 0;
        this.numEmpates = 0;
        this.numDerrotas = 0;
    }

    public BotId getNombre() {
        return nombre;
    }

    public void setNombre(BotId nombre) {
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

    public UsuarioId getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioId usuario) {
        this.usuario = usuario;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public List<ParticipacionId> getParticipaciones() {
        return participaciones;
    }

    public void setParticipaciones(List<ParticipacionId> participaciones) {
        this.participaciones = participaciones;
    }
}
