package com.adondeband.back_end_adonde_band.jpa.bot;

import com.adondeband.back_end_adonde_band.jpa.participacion.ParticipacionEntity;
import com.adondeband.back_end_adonde_band.jpa.entities.UsuarioEntity;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionId;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.jpa.enfrentamiento.EnfrentamientoJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.imagen.ImagenJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.participacion.ParticipacionJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses =   {
            EnfrentamientoJpaMapper.class,
            ParticipacionJpaMapper.class,
            ImagenJpaMapper.class,
        }, componentModel = "spring")
public interface BotJpaMapper {
    BotJpaMapper INSTANCE = Mappers.getMapper(BotJpaMapper.class);

    // Mapea de BotEntity a Bot
    Bot toDomain(BotEntity botEntity);

    // Mapea de Bot a BotEntity
    BotEntity toEntity(Bot bot);

    //TODO Terrorismo, va porque dios lo quiere
    ParticipacionEntity map(ParticipacionId value);

    // Mapeo de atributos
    default BotId toBotId(String value) {
        return new BotId(value);
    }

    default String toBotIdString(BotId id) {
        return id.value();
    }

    default UsuarioId toUsuarioId(UsuarioEntity entity) {
        return new UsuarioId(entity.getNombre());
    }

    default ParticipacionId toParticipacionId(ParticipacionEntity entity) {
        return new ParticipacionId(entity.getId());
    }
}
