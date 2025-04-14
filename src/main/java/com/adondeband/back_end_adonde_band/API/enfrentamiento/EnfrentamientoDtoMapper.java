package com.adondeband.back_end_adonde_band.API.enfrentamiento;

import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.enfrentamiento.Enfrentamiento;
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
    @Mapping(target = "fighters", expression = "java(botsToStringArray(enfrentamiento.getLocal(), enfrentamiento.getVisitante()))")
    EnfrentamientoDTO toDTO (Enfrentamiento enfrentamiento);

    default String[] botsToStringArray(BotId local, BotId visitante) {
        String[] res = new String[2];

        res[0] = (local != null) ? local.value() : "null";
        res[1] = (visitante != null) ? visitante.value() : "null";

        return res;
    }
}
