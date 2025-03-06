package com.adondeband.back_end_adonde_band.JPA.entities;


import com.adondeband.back_end_adonde_band.api.dominio.modelos.Bot;
import jakarta.persistence.*;

@Entity
@Table
public class Enfrentamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private ESTADO estado;
    private boolean ganador;

    @ManyToOne
    private Bot bot1;

    @ManyToOne
    private Bot bot2;

    @OneToOne
    private Conversacion conversacion;

    @ManyToOne
    private Jornada jornada;

    Bot getGanador() {
        return ganador ? bot1 : bot2;
    }
}
