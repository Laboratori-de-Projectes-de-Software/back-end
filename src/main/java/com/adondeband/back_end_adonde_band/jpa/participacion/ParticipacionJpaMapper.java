package com.adondeband.back_end_adonde_band.jpa.participacion;

import com.adondeband.back_end_adonde_band.jpa.bot.BotEntity;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaEntity;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParticipacionJpaMapper {
    ParticipacionJpaMapper INSTANCE = Mappers.getMapper(ParticipacionJpaMapper.class);

    // Mapea de ParticipacionEntity a Participacion
    Participacion toDomain(ParticipacionEntity participacionEntity);

    // Mapea de Participacion a ParticipacionEntity
    ParticipacionEntity toEntity(Participacion participacion);

    // Mapeo de atributos
    default BotId toBotId(BotEntity entity) {

        int victorias = entity.getNumVictorias();

        return new BotId(entity.getNombre());
    }

    default LigaId toLigaId(LigaEntity entity) {
        return new LigaId(entity.getId());
    }
}
