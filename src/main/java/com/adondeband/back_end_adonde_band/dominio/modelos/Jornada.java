package com.adondeband.back_end_adonde_band.dominio.modelos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Jornada {

    private int numJornada;

    private Liga liga;

    private List<Enfrentamiento> enfrentamientos;
}
