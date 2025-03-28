package org.example.backend.databaseapi.application.port.in.resultado;

import org.example.backend.databaseapi.domain.Resultado;

import java.util.List;

public interface BuscarResultadosBotPort {

    List<Resultado> buscarResultadosBot(Integer botId);
}
