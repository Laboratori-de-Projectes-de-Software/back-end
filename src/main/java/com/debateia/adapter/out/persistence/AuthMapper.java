package com.debateia.adapter.out.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.debateia.domain.User;
import com.debateia.adapter.in.rest.UserDto;

@Mapper
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    UserDto usuarioToDto(User usuario);
    User dtoToUsuario(UserDto usuarioDto);
}

