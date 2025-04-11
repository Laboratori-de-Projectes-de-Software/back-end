package com.adondeband.back_end_adonde_band.jpa.usuario;

import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionId;
import com.adondeband.back_end_adonde_band.dominio.rol.RolId;
import com.adondeband.back_end_adonde_band.dominio.usuario.CorreoId;
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
import org.springframework.beans.factory.annotation.Autowired;

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

    UsuarioEntity toEntity(UsuarioId id);

    default CorreoId map(String correo) {
        if (correo == null) {
            return null;
        }
        return new CorreoId(correo);
    }

    default String map(CorreoId correoId) {
        if (correoId == null) {
            return null;
        }
        return correoId.value(); // Assuming CorreoId has a `getValue` method.
    }

    // Mapeo de atributos
    default UsuarioId toUsuarioId(Long value) {
        if (value == null) {
            return null;
        }
        return new UsuarioId(value);
    }

    default Long UserToLong(UsuarioId id) {
        if (id == null) {
            return null;
        }
        return id.value();
    }

    default RolId toRolId(RolEntity entity) {
        if(entity == null) return null;
        return new RolId(entity.getNombre());
    }

    default BotId toBotId(BotEntity entity) {
        if (entity == null) {
            return null;
        }
        return new BotId(entity.getNombre());
    }

    default UsuarioId toUsuarioId(UsuarioEntity entity) {
        if(entity == null) return null;
        return new UsuarioId(entity.getId());
    }
}
