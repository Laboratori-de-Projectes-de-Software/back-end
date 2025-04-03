package com.adondeband.back_end_adonde_band.API.bot;

import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class BotDTO {

    private String nombre;

    private String cualidad;

    private String imagen; // luego quizá sea ImagenDTO

    private String endpoint;

    private Integer numVictorias;

    private Integer numEmpates;

    private Integer numDerrotas;

    private UsuarioId usuario;

    /*
    public BotDTO(String nombre, String cualidad, String imagen, String endpoint) {
        this.nombre = nombre;
        this.cualidad = cualidad;
        this.imagen = imagen;
        this.endpoint = endpoint;
        this.numVictorias = 0;
        this.numEmpates = 0;
        this.numDerrotas = 0;
        this.usuario = null;
    }
    */
}
