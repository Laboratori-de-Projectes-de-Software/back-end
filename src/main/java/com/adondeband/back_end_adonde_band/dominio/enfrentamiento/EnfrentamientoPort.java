package com.adondeband.back_end_adonde_band.dominio.enfrentamiento;

public interface EnfrentamientoPort {
    Enfrentamiento save(Enfrentamiento enfrentamiento);

    Enfrentamiento findById(Long idPartido);
}
