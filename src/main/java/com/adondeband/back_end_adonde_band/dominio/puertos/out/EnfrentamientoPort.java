package com.adondeband.back_end_adonde_band.dominio.puertos.out;

import com.adondeband.back_end_adonde_band.dominio.modelos.Enfrentamiento;

public interface EnfrentamientoPort {
    Enfrentamiento save(Enfrentamiento enfrentamiento);
}
