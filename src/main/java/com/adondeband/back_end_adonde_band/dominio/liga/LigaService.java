package com.adondeband.back_end_adonde_band.dominio.liga;

import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoId;
import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;

import java.util.List;

public interface LigaService {

    Liga crearLiga(Liga liga);

    List<Liga> obtenerLigaPorId(LigaId id);

    List <Liga> obtenerTodasLasLigas();

    List<Liga> obtenerLigasPorUsuario(UsuarioId userId);

    Liga addBotToLiga(LigaId ligaId, BotId botId);

    List<Participacion> obtenerParticipacionesPorLiga(LigaId ligaId);

    Liga startLiga(Liga liga, List<EnfrentamientoId> enfrentamientos);
}
