package com.adondeband.back_end_adonde_band.jpa.rol;

import com.adondeband.back_end_adonde_band.dominio.rol.Rol;
import com.adondeband.back_end_adonde_band.dominio.rol.RolId;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioEntity;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses =   {
            UsuarioJpaMapper.class,
        }, componentModel = "spring")
public interface RolJpaMapper {
    RolJpaMapper INSTANCE = Mappers.getMapper(RolJpaMapper.class);

    // Mapea de BotEntity a Bot
    Rol toDomain(RolEntity rolEntity);

    // Mapea de Bot a BotEntity
    RolEntity toEntity(Rol rol);

    // Mapeo de atributos
    default RolId toRolId(String value) {
        if(value == null) return null;
        return new RolId(value);
    }

    default String toRolIdString(RolId id) {
        if(id == null) return null;
        return id.value();
    }

    default UsuarioId toUsuarioId(UsuarioEntity entity) {
        if(entity == null) return null;
        return new UsuarioId(entity.getNombre());
    }
}
