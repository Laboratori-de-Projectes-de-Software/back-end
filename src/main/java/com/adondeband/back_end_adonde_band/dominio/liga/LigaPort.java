package com.adondeband.back_end_adonde_band.dominio.liga;

import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import jakarta.transaction.Transactional;

import java.util.List;

public interface LigaPort {
    Liga save(Liga liga);

    Liga findById(LigaId id);

    List<Liga> findAll();

    List<Liga> findLigasUsuario(UsuarioId userId);

    List<Participacion> findParticipacionesLiga(LigaId ligaId);

    Liga actualizarUrlImagen(LigaId ligaId, String url);

    Liga actualizarNRondas(LigaId ligaId, int nrondas);

    Liga actualizarTiempoRonda(LigaId ligaId, int tiempoRonda);

    Liga actualizarBotsLiga(LigaId ligaId, List<BotId> bots);

    Liga addBotToLiga(LigaId ligaId, BotId botId);

}
