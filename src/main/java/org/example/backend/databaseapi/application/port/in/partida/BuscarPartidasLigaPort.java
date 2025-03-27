package org.example.backend.databaseapi.application.port.in.partida;

import org.example.backend.databaseapi.domain.partida.Partida;

import java.util.List;

public interface BuscarPartidasLigaPort {
    List<Partida> buscarLigaPartida(Integer idliga);
}
