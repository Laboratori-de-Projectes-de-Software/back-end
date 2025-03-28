package org.example.backend.databaseapi.application.port.out.partida;

import org.example.backend.databaseapi.domain.Partida;

import java.util.List;

public interface FindLigaPartidaPort {

    List<Partida> findLigaPartida(Integer idliga);

}
