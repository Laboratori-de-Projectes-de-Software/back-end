package org.example.backend.databaseapi.application.port.out.partida;

import org.example.backend.databaseapi.domain.Liga;
import org.example.backend.databaseapi.domain.Partida;

import java.sql.Date;

public interface CreatePartidaPort {

    Partida createPartida(Partida partida);

}
