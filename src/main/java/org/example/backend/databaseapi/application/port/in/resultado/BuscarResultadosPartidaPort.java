package org.example.backend.databaseapi.application.port.in.resultado;

import org.example.backend.databaseapi.domain.Resultado;

import java.util.List;

public interface BuscarResultadosPartidaPort {

    List<Resultado> buscarResultadosPartida(Integer partidaId);
}
