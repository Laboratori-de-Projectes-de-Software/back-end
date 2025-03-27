package org.example.backend.databaseapi.jpa.usuario;

import org.example.backend.databaseapi.domain.usuario.Usuario;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioJpaMapper {

    Usuario toDomain(UsuarioJpaEntity entity);

    default UsuarioId toUserId(Integer value) {
        return new UsuarioId(value);
    }

    default UsuarioId toUserId(UsuarioJpaEntity entity) {
        return new UsuarioId(entity.getUserId());
    }

    default Integer toInteger(UsuarioId entity) {
        return entity.value();
    }
}
