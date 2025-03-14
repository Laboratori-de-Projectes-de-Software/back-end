package com.adondeband.back_end_adonde_band.dominio.puertos.out;

import com.adondeband.back_end_adonde_band.dominio.modelos.Jornada;

import java.util.List;

public interface JornadaPort {
    Jornada save(Jornada jornada);

    List<Jornada> findById(int s);
}
