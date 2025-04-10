package com.debateia.adapter.out.persistence;

import com.debateia.adapter.out.persistence.entities.UserEntity;
import org.mapstruct.Mapper;

import com.debateia.domain.User;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    User entityToUsuario(UserEntity usuarioEntity);

    UserEntity usuarioToEntity(User usuario);
}
