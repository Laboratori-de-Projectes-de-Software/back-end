package com.adondeband.back_end_adonde_band.jpa.participacion;

import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionId;
import com.adondeband.back_end_adonde_band.jpa.bot.BotEntity;
import com.adondeband.back_end_adonde_band.jpa.bot.BotJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaEntity;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BotJpaMapper.class, LigaJpaMapper.class})
public interface ParticipacionJpaMapper {
    ParticipacionJpaMapper INSTANCE = Mappers.getMapper(ParticipacionJpaMapper.class);

    // Mapea de ParticipacionEntity a Participacion
    Participacion toDomain(ParticipacionEntity participacionEntity);

    // Mapea de Participacion a ParticipacionEntity
    ParticipacionEntity toEntity(Participacion participacion);

    // Mapeo de atributos

    default ParticipacionId map(long value) {
        return new ParticipacionId(value);
    }

    default Long map(ParticipacionId participacionId) {
        return null;
    }
}