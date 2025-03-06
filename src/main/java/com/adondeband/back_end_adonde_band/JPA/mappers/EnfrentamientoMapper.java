package com.adondeband.back_end_adonde_band.JPA.mappers;

import com.adondeband.back_end_adonde_band.JPA.entities.EnfrentamientoEntity;
import com.adondeband.back_end_adonde_band.JPA.entities.ParticipacionEntity;
import com.adondeband.back_end_adonde_band.dominio.modelos.Enfrentamiento;
import com.adondeband.back_end_adonde_band.dominio.modelos.Participacion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnfrentamientoMapper {
    EnfrentamientoMapper INSTANCE = Mappers.getMapper(EnfrentamientoMapper.class);

    // Mapea de BotEntity a Bot
    Enfrentamiento toDomain(EnfrentamientoEntity enfrentamientoEntity);

    // Mapea de Bot a BotEntity
    EnfrentamientoEntity toEntity(Enfrentamiento enfrentamiento);

    // Mapea listas de EnfrentamientoEntity a Enfrentamiento
    List<Enfrentamiento> toDomain(List<EnfrentamientoEntity> enfrentamientosEntity);

    // Mapea listas de Enfrentamiento a EnfrentamientoEntity
    List<EnfrentamientoEntity> toEntity(List<Enfrentamiento> enfrentamientos);
}
