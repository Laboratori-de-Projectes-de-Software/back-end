package com.adondeband.back_end_adonde_band.dominio.liga;

import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LigaImpl implements LigaService {

    private final LigaPort ligaPort;

    public LigaImpl(LigaPort ligaPort) {
        this.ligaPort = ligaPort;
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
    public List<Participacion> obtenerParticipacionesPorLiga(LigaId ligaId) {
        return ligaPort.findParticipacionesLiga(ligaId);
    }
}
