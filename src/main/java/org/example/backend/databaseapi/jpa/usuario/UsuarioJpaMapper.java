package org.example.backend.databaseapi.jpa.usuario;

import org.example.backend.databaseapi.domain.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioJpaMapper {

    Usuario toDomain(UsuarioJpaEntity entity);
    UsuarioJpaEntity toEntity(Usuario usuario);
}
