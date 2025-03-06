package com.adondeband.back_end_adonde_band.dominio.modelos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Participacion {

    private long id;

    private Bot bot;

    private Liga liga;

    private int posicion;
    private int puntuacion;
}
