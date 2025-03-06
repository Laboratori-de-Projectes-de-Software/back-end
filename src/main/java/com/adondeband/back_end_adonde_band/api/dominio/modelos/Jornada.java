package com.adondeband.back_end_adonde_band.api.dominio.modelos;

import jakarta.persistence.*;

import java.util.List;

public class Jornada {

    private int numJornada;

    private Liga liga;

    private List<Enfrentamiento> enfrentamientos;
}
