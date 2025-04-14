package com.adondeband.back_end_adonde_band.jpa.enfrentamiento;

import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoId;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import com.adondeband.back_end_adonde_band.jpa.bot.BotEntity;
import com.adondeband.back_end_adonde_band.jpa.bot.BotJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.jornada.JornadaJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.participacion.ParticipacionJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {
        JornadaJpaMapper.class,
        BotJpaMapper.class,
        //ParticipacionJpaMapper.class
})
public interface EnfrentamientoJpaMapper {
    EnfrentamientoJpaMapper INSTANCE = Mappers.getMapper(EnfrentamientoJpaMapper.class);

    // Mapea de BotEntity a Bot
    Enfrentamiento toDomain(EnfrentamientoEntity enfrentamientoEntity);

    // Mapea de Bot a BotEntity
    EnfrentamientoEntity toEntity(Enfrentamiento enfrentamiento);

    default Long enfrentamientoIdToDTO (EnfrentamientoId id) {
        if (id == null) return null;

        return id.value();
    }

    default EnfrentamientoId toEnfrentamientoId (Long id){
        if (id == null) return null;

        return new EnfrentamientoId(id);
    }
}
