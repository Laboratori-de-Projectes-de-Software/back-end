package com.adondeband.back_end_adonde_band.dominio.enfrentamiento;

import com.adondeband.back_end_adonde_band.dominio.conversacion.Conversacion;

import java.util.List;

public interface EnfrentamientoService {

    Enfrentamiento insertarEnfrentamiento(Enfrentamiento enfrentamiento);

    List<Enfrentamiento> obtenerEnfrentamiento(Long idPartido);

    Enfrentamiento actualizarConversacion(EnfrentamientoId enfrentamientoId, Conversacion conversacion);
}
