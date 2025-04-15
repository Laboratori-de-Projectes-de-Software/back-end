package com.adondeband.back_end_adonde_band.API.enfrentamiento;

import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.EnfrentamientoId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnfrentamientoDtoMapper {
    EnfrentamientoDtoMapper INSTANCE = Mappers.getMapper(EnfrentamientoDtoMapper.class);

    // Mapear de DTO a dominio
    //Enfrentamiento toDomain (EnfrentamientoDTO enfrentamientoDTO);

    // Mapear de dominio a DTO
    @Mapping(target = "state", source = "estado")
    @Mapping(target = "result", source = "resultado")
    @Mapping(target = "fighters", expression = "java(botsToLongArray(enfrentamiento.getLocal(), enfrentamiento.getVisitante()))")
    EnfrentamientoDTO toDTO (Enfrentamiento enfrentamiento);

    default Long[] botsToLongArray(BotId local, BotId visitante) {
        Long[] res = new Long[2];

        res[0] = (local != null) ? local.value() : null;
        res[1] = (visitante != null) ? visitante.value() : null;

        return res;
    }

    default Long enfrentamientoIdToDTO (EnfrentamientoId id) {
        return id.value();
    }

    default EnfrentamientoId toEnfrentamientoId (Long id){
        return new EnfrentamientoId(id);
    }
}
