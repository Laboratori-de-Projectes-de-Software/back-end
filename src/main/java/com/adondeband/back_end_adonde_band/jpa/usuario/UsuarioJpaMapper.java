package com.adondeband.back_end_adonde_band.jpa.usuario;

import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionId;
import com.adondeband.back_end_adonde_band.dominio.rol.RolId;
import com.adondeband.back_end_adonde_band.dominio.usuario.Usuario;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import com.adondeband.back_end_adonde_band.jpa.bot.BotEntity;
import com.adondeband.back_end_adonde_band.jpa.bot.BotJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.imagen.ImagenEntity;
import com.adondeband.back_end_adonde_band.jpa.imagen.ImagenJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.rol.RolEntity;
import com.adondeband.back_end_adonde_band.jpa.rol.RolJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses =   {
            RolJpaMapper.class,
            BotJpaMapper.class,
            ImagenJpaMapper.class,
        }, componentModel = "spring")
public interface UsuarioJpaMapper {
    UsuarioJpaMapper INSTANCE = Mappers.getMapper(UsuarioJpaMapper.class);

    // Mapea de UsuarioEntity a Usuario
    Usuario toDomain(UsuarioEntity usuarioEntity);

    // Mapea de Usuario a UsuarioEntity
    UsuarioEntity toEntity(Usuario usuario);

    // Mapeo de atributos
    default UsuarioId toUsuarioId(String value) {
        return new UsuarioId(value);
    }

    default String toUsuarioIdString(UsuarioId id) {
        return id.value();
    }

    default RolId toRolId(RolEntity entity) {
        return new RolId(entity.getNombre());
    }

    default BotId toBotId(BotEntity entity) {
        return new BotId(entity.getNombre());
    }
}
