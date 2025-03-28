package org.example.backend.databaseapi.application.port.in.partida;

import org.example.backend.databaseapi.domain.Partida;

public interface BuscarPartidaPort {
    Partida buscarPartida(Integer idPartida);
}
