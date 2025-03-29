package org.example.backend.databaseapi.jpa.usuario;

import org.example.backend.databaseapi.domain.usuario.Email;
import org.example.backend.databaseapi.domain.usuario.Usuario;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsuarioJpaMapper {

    Usuario toDomain(UsuarioJpaEntity entity);

    UsuarioJpaEntity updateUser(UsuarioJpaEntity newUser, @MappingTarget UsuarioJpaEntity actualUser);

    default UsuarioId toUserId(Integer value) {
        return new UsuarioId(value);
    }

    default Email toEmail(String value){
        return new Email(value);
    }

    default String toString(Email email){
        return email.value();
    }

    default UsuarioId toUserId(UsuarioJpaEntity entity) {
        return new UsuarioId(entity.getUserId());
    }

    default Integer toInteger(UsuarioId entity) {
        return entity.value();
    }
}
