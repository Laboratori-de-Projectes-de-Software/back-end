package com.debateia.adapter.mapper;

import com.debateia.adapter.in.rest.league.ParticipationDTO;
import com.debateia.adapter.out.bot.BotEntity;
import com.debateia.adapter.out.league.LeagueEntity;

import com.debateia.adapter.out.participation.ParticipationEntity;
import com.debateia.domain.Participation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PartMapper {
    
    PartMapper INSTANCE = Mappers.getMapper(PartMapper.class);
    
    @Mapping(target = "name", ignore = true) // <- The names of the bot cannot be directly obtained from the
    Participation toDomain(ParticipationEntity entity); // ParticipationEntity. We add it after the conversion manually.
    
    ParticipationDTO toDTO(Participation domain);

    @Mapping(target = "league", source = "leagueId", qualifiedByName = "leagueIdToEntity")
    @Mapping(target = "bot", source = "botId", qualifiedByName = "mapBotIdToEntity")
    @Mapping(target = "nWins", source = "NWins")
    @Mapping(target = "nDraws", source = "NDraws")
    @Mapping(target = "nLoses", source = "NLoses")
    ParticipationEntity toEntity(Participation domain);
    
    @Named("leagueIdToEntity")
    default LeagueEntity leagueIdToEntity(Integer leagueId) {
        if (leagueId == null) return null;
        LeagueEntity dummyL = new LeagueEntity();
        dummyL.setId(leagueId);
        return dummyL;
    }
    
    @Named("mapBotIdToEntity")
    default BotEntity mapBotIdToEntity(Integer botId) {
        if (botId == null) return null;
        BotEntity dummy = new BotEntity();
        dummy.setId(botId);
        return dummy;
    }
    
}