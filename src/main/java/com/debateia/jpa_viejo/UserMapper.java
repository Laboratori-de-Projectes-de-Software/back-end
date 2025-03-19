package com.debateia.jpa_viejo;

import com.debateia.adapter.out.persistence.UserEntity;
import com.debateia.domain_viejo.UsuarioDto;

public interface UserMapper {
    UsuarioDto toDomain(UserEntity entity);
    UserEntity toEntity(UsuarioDto dto);
    
}
