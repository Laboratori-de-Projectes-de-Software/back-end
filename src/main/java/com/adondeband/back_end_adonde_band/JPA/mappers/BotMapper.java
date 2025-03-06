package com.adondeband.back_end_adonde_band.JPA.mappers;


import com.adondeband.back_end_adonde_band.api.dominio.modelos.Bot;
import com.adondeband.back_end_adonde_band.JPA.entities.BotEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BotMapper {
    BotMapper INSTANCE = Mappers.getMapper(BotMapper.class);

    // Mapea de BotEntity a Bot
    Bot toDomain(BotEntity botEntity);

    // Mapea de Bot a BotEntity
    BotEntity toEntity(Bot bot);
}
