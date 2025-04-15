package com.adondeband.back_end_adonde_band.dominio.enfrentamiento;

import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.conversacion.Conversacion;

import java.util.List;

public interface EnfrentamientoPort {
    Enfrentamiento save(Enfrentamiento enfrentamiento);

    List<Enfrentamiento> findById(Long idPartido);

    List<Enfrentamiento> findEnfrentamientosByLiga(Liga liga);
    Enfrentamiento actualizarConversacion(EnfrentamientoId enfrentamientoId, Conversacion conversacion);
}
