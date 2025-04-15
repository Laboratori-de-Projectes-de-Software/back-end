package com.adondeband.back_end_adonde_band.dominio.liga;

import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.exception.NotFoundException;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoId;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoService;
import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigaImpl implements LigaService {

    private final LigaPort ligaPort;
    private final EnfrentamientoService enfrentamientoService;

    public LigaImpl(LigaPort ligaPort, EnfrentamientoService enfrentamientoService) {
        this.ligaPort = ligaPort;
        this.enfrentamientoService = enfrentamientoService;
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
    public Liga startLiga(Liga liga) {
        // Crear Enfrentamientos
        List<Participacion> participaciones = obtenerParticipacionesPorLiga(liga.getId());
        List <EnfrentamientoId> enfrentamientos = enfrentamientoService.crearEnfrentamientosLiga(participaciones, liga.getId());
        // Asignar Enfrentamientos a la Liga
        liga.setEnfrentamientos(enfrentamientos);
        liga.setEstado(ESTADO.EN_CURSO);
        return crearLiga(liga);
    }
}
