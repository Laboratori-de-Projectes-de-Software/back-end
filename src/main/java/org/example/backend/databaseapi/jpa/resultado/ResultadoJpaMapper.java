package org.example.backend.databaseapi.jpa.resultado;

import org.example.backend.databaseapi.domain.Resultado;
import org.mapstruct.Mapper;

public interface ResultadoJpaMapper {

    Resultado toDomain(ResultadoJpaEntity entity);
    ResultadoJpaEntity toEntity(Resultado resultado);

}
