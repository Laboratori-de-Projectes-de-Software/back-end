package com.debateia.adapter.mapper;

import com.debateia.adapter.in.rest.league.State;
import com.debateia.adapter.in.rest.match.MatchResponseDTO;
import com.debateia.adapter.out.match.MatchEntity;
import com.debateia.domain.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);
    
    @Mapping(target = "matchId", source = "id")
    @Mapping(target = "state", source = "state", qualifiedByName = "stringToState")
    @Mapping(target = "bot1id", source = "bot1.id")
    @Mapping(target = "bot2id", source = "bot2.id")
    @Mapping(target = "leagueId", source = "league.id")
    @Mapping(target = "messageIds", ignore = true) // <- TODO (!)
    @Mapping(target = "fighters", ignore = true) // <- The names of the bots cannot be directly obtained
    Match toDomain(MatchEntity entity);          // from the MatchEntity. We add them after the conversion manually.
    
    @Mapping(target = "state", source = "state", qualifiedByName = "stateToString")
    @Mapping(target = "roundNumber", source = "roundNumber")
    @Mapping(target = "id", source = "matchId")
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "bot1", ignore = true)
    @Mapping(target = "bot2", ignore = true)
    @Mapping(target = "league", ignore = true)
    MatchEntity toEntity(Match dom);
    
    @Mapping(target = "matchId", source = "matchId")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "result", source = "result")
    @Mapping(target = "roundNumber", source = "roundNumber")
    MatchResponseDTO toResponseDTO(Match dom);
    
    @Named("stringToState")
    default State stringToState(String state) {
        if (state == null) return null;
        return State.valueOf(state);
    }
    
    @Named("stateToString")
    default String stateToString(State state) {
        if (state == null) return null;
        return state.toString();
    }
}