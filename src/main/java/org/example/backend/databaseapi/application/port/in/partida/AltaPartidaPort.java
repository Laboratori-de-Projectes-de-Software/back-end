package org.example.backend.databaseapi.application.port.in.partida;

import org.example.backend.databaseapi.domain.Partida;

public interface AltaPartidaPort {

    Partida altaPartida(Partida partida);
}
