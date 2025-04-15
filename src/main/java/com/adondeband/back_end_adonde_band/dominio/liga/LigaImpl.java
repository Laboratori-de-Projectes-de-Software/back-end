package com.adondeband.back_end_adonde_band.dominio.liga;

import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.bot.BotService;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoId;
import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
import com.adondeband.back_end_adonde_band.dominio.exception.NotFoundException;
import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigaImpl implements LigaService {

    private final LigaPort ligaPort;
    private final BotService botService;

    public LigaImpl(LigaPort ligaPort, BotService botService) {
        this.ligaPort = ligaPort;
        this.botService = botService;
    }

    @Override
    public Liga crearLiga(Liga liga) {
        return ligaPort.save(liga);
    }

    @Override
    public List<Liga> obtenerLigaPorId(LigaId id) {
        return ligaPort.findById(id);
    }

    @Override
    public List<Liga> obtenerTodasLasLigas() {
        return ligaPort.findAll();
    }

    @Override
    public List<Liga> obtenerLigasPorUsuario(UsuarioId userId) {
        return ligaPort.findLigasUsuario(userId);
    }

    @Override
    public Liga addBotToLiga(LigaId ligaId, BotId botId) {
        if (botService.obtenerBotPorId(botId.value()) == null) {
            throw new NotFoundException("Este bot no existe");
        }

        if (obtenerLigaPorId(ligaId).isEmpty()) {
            throw new NotFoundException("Esta liga no existe");
        }

        return ligaPort.addBotToLiga(ligaId, botId);
    }


    @Override
    public List<Participacion> obtenerParticipacionesPorLiga(LigaId ligaId) {
        if (obtenerLigaPorId(ligaId).isEmpty()) {
            throw new NotFoundException("Esta liga no existe");
        }

        return ligaPort.findParticipacionesLiga(ligaId);
    }

    @Override
    public Liga startLiga(Liga liga, List<EnfrentamientoId> enfrentamientos) {
        // Asignar Enfrentamientos a la Liga
        liga.setEnfrentamientos(enfrentamientos);
        liga.setEstado(ESTADO.EN_CURSO);
        return crearLiga(liga);
    }
}
