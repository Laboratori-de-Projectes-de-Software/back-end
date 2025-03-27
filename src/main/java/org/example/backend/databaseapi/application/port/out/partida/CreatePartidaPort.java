package org.example.backend.databaseapi.application.port.out.partida;

import org.example.backend.databaseapi.domain.partida.Partida;

public interface CreatePartidaPort {

    Partida createPartida(Partida partida);

}
