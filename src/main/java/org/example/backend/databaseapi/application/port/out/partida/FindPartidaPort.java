package org.example.backend.databaseapi.application.port.out.partida;

import org.example.backend.databaseapi.domain.partida.Partida;

import java.util.Optional;

public interface FindPartidaPort {

    Optional<Partida> findParida(Integer idPartida);

}
