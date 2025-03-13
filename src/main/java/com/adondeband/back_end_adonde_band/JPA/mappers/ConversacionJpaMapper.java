package com.adondeband.back_end_adonde_band.JPA.mappers;

import com.adondeband.back_end_adonde_band.JPA.entities.ConversacionEntity;
import com.adondeband.back_end_adonde_band.dominio.modelos.Conversacion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ConversacionJpaMapper {
    ConversacionJpaMapper INSTANCE = Mappers.getMapper(ConversacionJpaMapper.class);

    // Mapea de BotEntity a Bot
    Conversacion toDomain(ConversacionEntity enfrentamientoEntity);

    // Mapea de Bot a BotEntity
    ConversacionEntity toEntity(Conversacion conversacion);
}
