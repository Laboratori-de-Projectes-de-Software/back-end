package com.adondeband.back_end_adonde_band.dominio.enfrentamiento;

import java.util.List;

public interface EnfrentamientoPort {
    Enfrentamiento save(Enfrentamiento enfrentamiento);

    List<Enfrentamiento> findById(Long idPartido);


}
