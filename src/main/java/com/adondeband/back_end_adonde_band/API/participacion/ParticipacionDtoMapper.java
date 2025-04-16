package com.adondeband.back_end_adonde_band.API.participacion;

import com.adondeband.back_end_adonde_band.API.bot.BotDtoMapper;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionId;
import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {BotDtoMapper.class})
public abstract class ParticipacionDtoMapper {
    ParticipacionService participacionService;

    @Autowired
    public void setParticipacionService(ParticipacionService participacionService) {
        this.participacionService = participacionService;
    }

    // toDTO
    @Mapping(target = "position", source = "posicion")
    @Mapping(target = "points", source = "puntuacion")
    @Mapping(target = "NWins", source = "numVictorias")
    @Mapping(target = "NDraws", source = "numEmpates")
    @Mapping(target = "NLosses", source = "numDerrotas")
    @Mapping(target = "botId", source = "bot")
    public abstract ParticipacionDTO toDTO(Participacion participacion);

    public List<Long> toListId(List<ParticipacionId> ids) {
        if (ids == null || ids.isEmpty()) {
            // return empty list
            return new ArrayList<>();
        }

        return ids.stream()
                .map(participacionService::obtenerParticipacion) // Obtener Participacion
                .map(Participacion::getBot) // Obtener BotId
                .map(BotId::value) // Obtener valor Long de BotId
                .collect(Collectors.toList()); // Colectar en una lista
    }
}
