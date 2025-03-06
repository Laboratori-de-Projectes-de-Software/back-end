package com.adondeband.back_end_adonde_band.API.mappers;

import com.adondeband.back_end_adonde_band.API.DTO.BotDTO;
import com.adondeband.back_end_adonde_band.JPA.mappers.EnfrentamientoMapper;
import com.adondeband.back_end_adonde_band.JPA.mappers.ParticipacionMapper;
import com.adondeband.back_end_adonde_band.dominio.modelos.Bot;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses =  {EnfrentamientoMapper.class, ParticipacionMapper.class}, componentModel = "spring")
public interface BotMapper {
    BotMapper INSTANCE = Mappers.getMapper(BotMapper.class);

    // Mapear de DTO a Dominio
    Bot toDomain(BotDTO botDTO);

    // Mapea de Bot a BotDTO
    BotDTO toDTO(Bot bot);

}
