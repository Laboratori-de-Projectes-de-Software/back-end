package com.debateia.adapter.mapper;

import com.debateia.adapter.in.rest.league.ParticipationDTO;
import com.debateia.adapter.out.participation.ParticipationEntity;
import com.debateia.domain.Participation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PartMapper {
    
    PartMapper INSTANCE = Mappers.getMapper(PartMapper.class);
    
    @Mapping(target = "name", ignore = true) // <- The names of the bot cannot be directly obtained from the
    Participation toDomain(ParticipationEntity entity); // ParticipationEntity. We add it after the conversion manually.
    
    ParticipationResponseDTO toDTO(Participation domain);

    @Mapping(target = "league", ignore = true)
    @Mapping(target = "bot", ignore = true)
    ParticipationEntity toEntity(Participation domain);
}