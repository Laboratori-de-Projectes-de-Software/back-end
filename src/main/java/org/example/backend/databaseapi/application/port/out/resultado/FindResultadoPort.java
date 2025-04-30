package org.example.backend.databaseapi.application.port.out.resultado;


import org.example.backend.databaseapi.domain.resultado.Resultado;

import java.util.Optional;

public interface FindResultadoPort {

    Optional<Resultado> findResultado(Integer botId, Integer partidaId);

}
