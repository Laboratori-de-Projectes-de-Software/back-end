package com.debateia.domain_viejo;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.debateia.JWT_viejo.User;
import com.debateia.JWT_viejo.UserDto;

@Mapper
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    UserDto usuarioToDto(User usuario);
    User dtoToUsuario(UserDto usuarioDto);
}

