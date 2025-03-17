package com.debateia.domain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.debateia.JWT.User;
import com.debateia.JWT.UserDto;

@Mapper
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    UserDto usuarioToDto(User usuario);
    User dtoToUsuario(UserDto usuarioDto);
}

