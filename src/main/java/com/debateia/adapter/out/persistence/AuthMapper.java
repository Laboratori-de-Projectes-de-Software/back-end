package com.debateia.adapter.out.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.debateia.domain.User;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    User entityToUsuario(UserEntity usuarioEntity);

    UserEntity usuarioToEntity(User usuario);
}
