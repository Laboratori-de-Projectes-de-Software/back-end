package com.debateia.adapter.mapper;

import com.debateia.adapter.in.rest.league.State;
import com.debateia.adapter.in.rest.league.CreateLeagueDTO;
import com.debateia.adapter.in.rest.league.LeagueDTO;
import com.debateia.adapter.out.league.LeagueEntity;
import com.debateia.adapter.out.participation.ParticipationEntity;
import com.debateia.adapter.out.user.UserEntity;
import com.debateia.domain.League;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LeagueMapper {
    
    LeagueMapper INSTANCE = Mappers.getMapper(LeagueMapper.class);
    
    @Mapping(target = "state", source = "state", qualifiedByName = "mapStateToEnum")
    @Mapping(target = "bots", source = "botIds")
    LeagueDTO toLeagueResponseDTO(League league);
    
    @Mapping(target = "state", constant = "PENDING")
    @Mapping(target = "leagueId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "matchIds", ignore = true)
    League toDomain(CreateLeagueDTO dto);
    
    @Mapping(target = "leagueId", source = "id")
    @Mapping(target = "matchIds", source = "matches", qualifiedByName = "mapMatchesToIds")
    @Mapping(target = "botIds", source = "participations", qualifiedByName = "mapParticipationsToIds")
    @Mapping(target = "userId", source = "user.id")
    League toDomain(LeagueEntity entity);
    
    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUserIdToEntity")
    @Mapping(target = "matches", expression = "java(new ArrayList<>())")
    @Mapping(target = "participations", source = "botIds", qualifiedByName = "mapBotIdsToParticipations")
    @Mapping(target = "id", source = "leagueId")
    LeagueEntity toEntity(League league);
    
    @Named("mapStateToEnum")
    default State mapStateToEnum(String state) {
        try {
            return State.valueOf(state);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Named("mapMatchesToIds")
    default List<Integer> mapMatchesToIds(List<? extends Object> matches) {
        if (matches == null) return null;
        return matches.stream()
                .map(match -> {
                    try {
                        return (Integer) match.getClass().getMethod("getId").invoke(match);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
    
    @Named("mapParticipationsToIds")
    default List<Integer> mapParticipationsToIds(List<ParticipationEntity> participations) {
        if (participations == null) return null;
        return participations.stream()
                .map(ParticipationEntity::getBotId)
                .collect(Collectors.toList());
    }
    
    @Named("mapUserIdToEntity")
    default UserEntity mapUserIdToEntity(Integer userId) {
        if (userId == null) return null;
        UserEntity dummy = new UserEntity();
        dummy.setId(userId);
        return dummy;
    }
    
    @Named("mapBotIdsToParticipations")
    default List<ParticipationEntity> mapBotIdsToParticipations(List<Integer> botIds) {
        if (botIds == null) return null;
        return botIds.stream()
                .map(botId -> {
                    ParticipationEntity entity = new ParticipationEntity();
                    entity.setLeagueId(null); // This will be set after the league is created
                    entity.setBotId(botId);
                    entity.setPoints(0);
                    entity.setPosition(0);
                    entity.setNDraws(0);
                    entity.setNWins(0);
                    entity.setNLoses(0);
                    return entity;
                })
                .collect(Collectors.toList());
    }
}