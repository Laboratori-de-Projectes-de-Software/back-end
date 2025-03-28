package org.example.backend.databaseapi.jpa.liga;

import org.example.backend.databaseapi.domain.Liga;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LigaJpaMapper {

    LigaJpaEntity toEntity(Liga liga);

    Liga toDomain(LigaJpaEntity entity);

}
