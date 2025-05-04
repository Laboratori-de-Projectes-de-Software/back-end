package com.adondeband.back_end_adonde_band.jpa.conversacion;

import com.adondeband.back_end_adonde_band.dominio.conversacion.Conversacion;
import com.adondeband.back_end_adonde_band.jpa.enfrentamiento.EnfrentamientoJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {EnfrentamientoJpaMapper.class})
public interface ConversacionJpaMapper {
    ConversacionJpaMapper INSTANCE = Mappers.getMapper(ConversacionJpaMapper.class);

    // Mapea de ConversacionEntity a Conversacion
    Conversacion toDomain(ConversacionEntity enfrentamientoEntity);

    // Mapea de Conversacion a ConversacionEntity
    ConversacionEntity toEntity(Conversacion conversacion);
}
