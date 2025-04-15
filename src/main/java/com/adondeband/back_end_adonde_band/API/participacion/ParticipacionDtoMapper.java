package com.adondeband.back_end_adonde_band.API.participacion;

import com.adondeband.back_end_adonde_band.API.bot.BotDtoMapper;
import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BotDtoMapper.class})
public interface ParticipacionDtoMapper {
    ParticipacionDtoMapper INSTANCE = Mappers.getMapper(ParticipacionDtoMapper.class);

    // toDTO
    @Mapping(target = "position", source = "posicion")
    @Mapping(target = "points", source = "puntuacion")
    @Mapping(target = "NWins", source = "numVictorias")
    @Mapping(target = "NDraws", source = "numEmpates")
    @Mapping(target = "NLosses", source = "numDerrotas")
    @Mapping(target = "botId", source = "bot")
    ParticipacionDTO toDTO(Participacion participacion);
}
