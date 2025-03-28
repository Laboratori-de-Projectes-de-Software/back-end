package org.example.backend.databaseapi.jpa.mensaje;

import org.example.backend.databaseapi.domain.Mensaje;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MensajeJpaMapper {

    Mensaje toDomain(MensajeJpaEntity entity);

    MensajeJpaEntity toEntity(Mensaje mensaje);

}
