package com.adondeband.back_end_adonde_band.jpa.enfrentamiento;

import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoId;
import com.adondeband.back_end_adonde_band.jpa.bot.BotJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.jornada.JornadaJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {
        JornadaJpaMapper.class,
        BotJpaMapper.class
})
public interface EnfrentamientoJpaMapper {
    EnfrentamientoJpaMapper INSTANCE = Mappers.getMapper(EnfrentamientoJpaMapper.class);

    // Mapea de BotEntity a Bot
    Enfrentamiento toDomain(EnfrentamientoEntity enfrentamientoEntity);

    // Mapea de Bot a BotEntity
    EnfrentamientoEntity toEntity(Enfrentamiento enfrentamiento);

    default long toLongId(EnfrentamientoId enfrentamientoId){return enfrentamientoId.value();}

    default EnfrentamientoId toEnfrentamientoId(long id){return new EnfrentamientoId(id);}
}
