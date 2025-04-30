package org.example.backend.databaseapi.application.port.in.resultado;

import org.example.backend.databaseapi.domain.resultado.Resultado;

import java.util.List;

public interface BuscarResultadosLiga {

    List<Resultado> buscarResultadosLiga(Integer idLiga);
}
