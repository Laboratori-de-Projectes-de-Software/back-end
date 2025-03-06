package com.adondeband.back_end_adonde_band.JPA.mappers;

import com.adondeband.back_end_adonde_band.JPA.entities.ParticipacionEntity;
import com.adondeband.back_end_adonde_band.dominio.modelos.Participacion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses =  { LigaMapper.class , BotMapper.class}, componentModel = "spring")
public interface ParticipacionMapper {
    ParticipacionMapper INSTANCE = Mappers.getMapper(ParticipacionMapper.class);

    // Mapea de ParticipacionEntity a Participacion
    Participacion toDomain(ParticipacionEntity participacionEntity);

    // Mapea de Participacion a ParticipacionEntity
    ParticipacionEntity toEntity(Participacion participacion);

    // Mapea listas de ParticipacionEntity a Participacion
    List<Participacion> toDomain(List<ParticipacionEntity> participacionesEntity);

    // Mapea listas de Participacion a ParticipacionEntity
    List<ParticipacionEntity> toEntity(List<Participacion> participaciones);
}
