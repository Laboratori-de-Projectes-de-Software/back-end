package org.example.backend.databaseapi.application.port.out.resultado;

import org.example.backend.databaseapi.domain.resultado.Resultado;

import java.util.List;

public interface FindLigaResultadoPort {

    List<Resultado> findResultadoLiga(Integer ligaId);
}
