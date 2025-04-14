package com.adondeband.back_end_adonde_band.dominio.liga;

import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;

import java.util.List;

public interface LigaPort {
    Liga save(Liga liga);

    List<Liga> findById(LigaId id);

    List<Liga> findAll();

    List<Liga> findLigasUsuario(UsuarioId userId);

    List<Participacion> findParticipacionesLiga(LigaId ligaId);

    Liga actualizarUrlImagen(LigaId ligaId, String url);

    Liga actualizarnrondas(LigaId ligaId, int nrondas);

    Liga actualizarTiempoRonda(LigaId ligaId, int tiempoRonda);

    Liga actualizarBots(LigaId ligaId, List<BotId> bots);

    Liga addBotToLiga(LigaId ligaId, BotId botId);
}
