package com.adondeband.back_end_adonde_band.dominio.modelos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Liga {

    private long id;

    private String nombre;

    private ESTADO estado;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    private Imagen imagen;

    private List<Participacion> participaciones;
}
