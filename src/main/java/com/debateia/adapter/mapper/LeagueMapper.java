package com.debateia.adapter.mapper;

import com.debateia.adapter.in.web.dto.State;
import com.debateia.adapter.in.web.dto.request.LeagueDTO;
import com.debateia.adapter.in.web.dto.response.LeagueResponseDTO;
import com.debateia.adapter.out.persistence.entities.LeagueEntity;
import com.debateia.domain.League;
import org.mapstruct.*;

@Mapper(componentModel = "spring", 
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserMapper.class, MatchMapper.class})
public interface LeagueMapper {

    // Convert from LeagueDTO to Domain League
    @Mapping(target = "leagueId", ignore = true)
    @Mapping(target = "matches", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "user", ignore = true)
    League toLeagueDomain(LeagueDTO leagueDTO);

    // Convert from Domain League to LeagueResponseDTO
    @Mapping(target = "user", source = "user.userId")
    @Mapping(target = "state", expression = "java(mapStateToEnum(league.getState()))")
    LeagueResponseDTO toLeagueResponseDTO(League league);

    // Convert from Domain League to LeagueEntity
    LeagueEntity toLeagueEntity(League league);

    // Convert from LeagueEntity to Domain League
    League toLeagueDomain(LeagueEntity leagueEntity);

    // Los mappers de los atributos de esta clase no podran mapear los campos que apunten a esta clase
    // Ya que aun no habra sido creada. Por lo tanto, es necesario hacer esto.
    @AfterMapping
    default void setMatchLeagueReferences(@MappingTarget LeagueEntity leagueEntity) {
        if (leagueEntity.getMatches() != null) {
            leagueEntity.getMatches().forEach(match -> match.setLeague(leagueEntity));
        }
    }
    
    // Helper method to map string state to enum State
    default State mapStateToEnum(String state) {
        if (state == null) {
            return null;
        }
        try {
            return State.valueOf(state);
        } catch (IllegalArgumentException e) {
            // Default value if state string doesn't match any enum value
            return State.PENDIENTE;
        }
    }

    default String mapEnumToState(State state) {
        return state != null ? state.name() : null;
    }
}