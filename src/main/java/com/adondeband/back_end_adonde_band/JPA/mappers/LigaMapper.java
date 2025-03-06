package com.adondeband.back_end_adonde_band.JPA.mappers;

import com.adondeband.back_end_adonde_band.JPA.entities.LigaEntity;
import com.adondeband.back_end_adonde_band.dominio.modelos.Liga;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LigaMapper {
    LigaMapper INSTANCE = Mappers.getMapper(LigaMapper.class);

    // Mapea de BotEntity a Bot
    Liga toDomain(LigaEntity ligaEntity);

    // Mapea de Bot a BotEntity
    LigaEntity toEntity(Liga liga);
}
