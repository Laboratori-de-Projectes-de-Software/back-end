package org.example.backend.databaseapi.application.port.out.resultado;

import org.example.backend.databaseapi.domain.Bot;
import org.example.backend.databaseapi.domain.Partida;
import org.example.backend.databaseapi.domain.Resultado;

public interface CreateResultadoPort {

    Resultado createResultado(Resultado resultado);

}
