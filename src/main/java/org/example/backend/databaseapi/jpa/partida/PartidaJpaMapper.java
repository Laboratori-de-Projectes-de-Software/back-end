package org.example.backend.databaseapi.jpa.partida;

import org.example.backend.databaseapi.domain.Partida;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartidaJpaMapper {

    Partida toDomain(PartidaJpaEntity entity);
    PartidaJpaEntity toEntity(Partida partida);

}
