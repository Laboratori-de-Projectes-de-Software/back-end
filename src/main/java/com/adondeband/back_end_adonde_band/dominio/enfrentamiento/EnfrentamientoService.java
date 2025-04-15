package com.adondeband.back_end_adonde_band.dominio.enfrentamiento;

import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.dominio.conversacion.Conversacion;

import java.util.List;

public interface EnfrentamientoService {

    Enfrentamiento insertarEnfrentamiento(Enfrentamiento enfrentamiento);

    List<Enfrentamiento> obtenerEnfrentamiento(Long idPartido);

    List<EnfrentamientoId> crearEnfrentamientosLiga(List<Participacion> participaciones, LigaId ligaId);
    Enfrentamiento actualizarConversacion(EnfrentamientoId enfrentamientoId, Conversacion conversacion);
}
