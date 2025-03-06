package com.adondeband.back_end_adonde_band.api.dominio.modelos;


import jakarta.persistence.*;

public class Enfrentamiento {

    private long id;

    private ESTADO estado;
    private boolean ganador;

    private Bot bot1;

    private Bot bot2;

    private Conversacion conversacion;

    private Jornada jornada;

    Bot getGanador() {
        return ganador ? bot1 : bot2;
    }
}
