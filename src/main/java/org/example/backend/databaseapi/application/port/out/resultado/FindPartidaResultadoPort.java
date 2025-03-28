package org.example.backend.databaseapi.application.port.out.resultado;

import org.example.backend.databaseapi.domain.Resultado;

import java.util.List;

public interface FindPartidaResultadoPort {

    List<Resultado> findPartidaResultado(int idPartida);

}
