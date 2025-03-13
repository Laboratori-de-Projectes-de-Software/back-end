package com.adondeband.back_end_adonde_band.JPA.mappers;

import com.adondeband.back_end_adonde_band.JPA.entities.ParticipacionEntity;
import com.adondeband.back_end_adonde_band.JPA.entities.UsuarioEntity;
import com.adondeband.back_end_adonde_band.dominio.Ids.BotId;
import com.adondeband.back_end_adonde_band.dominio.Ids.ParticipacionId;
import com.adondeband.back_end_adonde_band.dominio.Ids.UsuarioId;
import com.adondeband.back_end_adonde_band.dominio.modelos.Bot;
import com.adondeband.back_end_adonde_band.JPA.entities.BotEntity;
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
