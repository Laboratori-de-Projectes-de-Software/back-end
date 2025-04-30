package org.example.backend.databaseapi.application.port.in.partida;

import org.example.backend.databaseapi.application.dto.partida.MatchDTOResponse;
import org.example.backend.databaseapi.domain.partida.Partida;

import java.util.List;

public interface BuscarPartidasLigaPort {
    List<MatchDTOResponse> buscarLigaPartida(Integer idliga);
}
