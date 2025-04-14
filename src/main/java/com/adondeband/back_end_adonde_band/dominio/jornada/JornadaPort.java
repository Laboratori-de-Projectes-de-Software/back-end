package com.adondeband.back_end_adonde_band.dominio.jornada;

import java.util.List;

public interface JornadaPort {
    Jornada save(Jornada jornada);

    List<Jornada> findById(Long s);
}
