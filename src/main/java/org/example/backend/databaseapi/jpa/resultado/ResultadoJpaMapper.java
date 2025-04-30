package org.example.backend.databaseapi.jpa.resultado;

import org.example.backend.databaseapi.domain.resultado.Resultado;

public interface ResultadoJpaMapper {

    Resultado toDomain(ResultadoJpaEntity entity);


}
