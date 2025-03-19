package com.debateia.jpa_viejo;

import com.debateia.adapter.out.persistence.UserEntity;
import org.springframework.stereotype.Component;

import com.debateia.domain_viejo.UsuarioDto;
@Component
public class UserMapperImp implements UserMapper {

    @Override
    public UsuarioDto toDomain(UserEntity entity) {
     return new UsuarioDto(entity.getNameUser(),entity.getEmail(),entity.getPassword());    
    }

    @Override
    public UserEntity toEntity(UsuarioDto dto) {
    UserEntity entity=new UserEntity();  
    entity.setId(dto.getId());
    entity.setNameUser(dto.getName());
    entity.setEmail(dto.getEmail());  
    entity.setPassword(dto.getPassword());
    return entity;
    }
    
}
