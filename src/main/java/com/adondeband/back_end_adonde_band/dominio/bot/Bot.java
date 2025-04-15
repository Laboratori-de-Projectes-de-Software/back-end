package com.adondeband.back_end_adonde_band.dominio.bot;

import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionId;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bot {

    private BotId id;

    private String nombre;

    private Integer numVictorias;

    private Integer numEmpates;

    private Integer numDerrotas;

    private String cualidad;

    private String endpoint;

    private UsuarioId usuario;

    private Imagen imagen;

    private List<ParticipacionId> participaciones;

    public Bot (String cualidad, String nombre) {
        this.nombre = nombre;
        this.cualidad = cualidad;
        this.numVictorias = 0;
        this.numEmpates = 0;
        this.numDerrotas = 0;
    }
}
