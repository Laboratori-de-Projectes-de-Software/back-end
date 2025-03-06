package com.adondeband.back_end_adonde_band.dominio.modelos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Conversacion {

    private long id;

    private String ficheroRuta;

    private Enfrentamiento enfrentamiento;
}
