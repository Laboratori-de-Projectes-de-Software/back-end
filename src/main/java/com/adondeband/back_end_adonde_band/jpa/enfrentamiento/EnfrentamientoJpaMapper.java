package com.adondeband.back_end_adonde_band.jpa.enfrentamiento;

import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoId;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import com.adondeband.back_end_adonde_band.jpa.bot.BotEntity;
import com.adondeband.back_end_adonde_band.jpa.bot.BotJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaEntity;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.participacion.ParticipacionJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {
        BotJpaMapper.class,
        LigaJpaMapper.class,
        //ParticipacionJpaMapper.class
})
public interface EnfrentamientoJpaMapper {
    EnfrentamientoJpaMapper INSTANCE = Mappers.getMapper(EnfrentamientoJpaMapper.class);

    // Mapea de BotEntity a Bot
    @Mapping(target = "ligaId",source = "liga")
    Enfrentamiento toDomain(EnfrentamientoEntity enfrentamientoEntity);

    // Mapea de Bot a BotEntity
    @Mapping(target = "liga",source = "ligaId")
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
