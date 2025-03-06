package com.adondeband.back_end_adonde_band.dominio.puertos.out;

import com.adondeband.back_end_adonde_band.dominio.modelos.Participacion;

public interface ParticipacionPort {
    Participacion save(Participacion participacion);
}
