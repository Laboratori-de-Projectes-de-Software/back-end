package com.adondeband.back_end_adonde_band.dominio.enfrentamiento;

import java.util.List;

public interface EnfrentamientoService {

    Enfrentamiento insertarEnfrentamiento(Enfrentamiento enfrentamiento);

    List<Enfrentamiento> obtenerEnfrentamiento(Long idPartido);
}
