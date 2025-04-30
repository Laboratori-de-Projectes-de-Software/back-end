package org.example.backend.databaseapi.application.port.in.partida;

import org.example.backend.databaseapi.domain.partida.Partida;

public interface BuscarPartidaPort {
    Partida buscarPartida(Integer idPartida);
}
