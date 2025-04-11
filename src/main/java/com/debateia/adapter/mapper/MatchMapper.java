package com.debateia.adapter.mapper;

import com.debateia.adapter.in.web.dto.State;
import com.debateia.adapter.in.web.dto.response.MatchResponseDTO;
import com.debateia.adapter.out.persistence.entities.MatchEntity;
import com.debateia.domain.Match;
import java.util.ArrayList;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", 
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
        //,uses = {LeagueMapper.class} 
        )
public interface MatchMapper {
    
    // Convert from MatchEntity to Match domain
    @Mapping(target = "matchId", source = "id")
    @Mapping(target = "messages", ignore = true)  // Messages mapping might need a separate mapper
    Match toMatchDomain(MatchEntity matchEntity);

    // Convert from Match domain to MatchEntity
    @Mapping(target = "id", source = "matchId")
    @Mapping(target = "messages", ignore = true)  // Messages mapping might need a separate mapper
    MatchEntity toMatchEntity(Match match);

    // Convert from Match domain to MatchResponseDTO
    @Mapping(target = "state", expression = "java(mapStateToEnum(match.getState()))")
    MatchResponseDTO toMatchResponseDTO(Match match);

    // Convert a list of MatchEntity to a list of Match domain
    List<Match> toMatchDomainList(List<MatchEntity> matchEntities);

    // Convert a list of Match domain to a list of MatchEntity
    List<MatchEntity> toMatchEntityList(List<Match> matches);

    // Convert a list of Match domain to a list of MatchResponseDTO
    List<MatchResponseDTO> toMatchResponseDTOList(List<Match> matches);

    // Update existing MatchEntity from Match domain
    @Mapping(target = "id", source = "matchId")
    @Mapping(target = "messages", ignore = true)
    void updateMatchEntityFromDomain(Match source, @MappingTarget MatchEntity target);

    // Update existing Match domain from MatchEntity
    @Mapping(target = "matchId", source = "id")
    @Mapping(target = "messages", ignore = true)
    void updateMatchDomainFromEntity(MatchEntity source, @MappingTarget Match target);

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

    // After mapping method to handle fighters list if needed
    @AfterMapping
    default void setFightersList(@MappingTarget Match match) {
        ArrayList<String> fighters = new ArrayList<>();
        fighters.add(match.getBot1().getTrait());
        fighters.add(match.getBot2().getTrait());
        match.setFighters(fighters);
    }
}