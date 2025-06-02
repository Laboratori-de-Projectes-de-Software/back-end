package com.debateia.adapter.mapper;

import com.debateia.adapter.in.rest.league.CreateLeagueDTO;
import com.debateia.adapter.in.rest.league.LeagueDTO;
import com.debateia.adapter.in.rest.league.State;
import com.debateia.adapter.out.league.LeagueEntity;
import com.debateia.adapter.out.match.MatchEntity;
import com.debateia.adapter.out.participation.ParticipationEntity;
import com.debateia.adapter.out.participation.ParticipationJpaRepository;
import com.debateia.adapter.out.user.UserEntity;
import com.debateia.adapter.out.user.UserJpaRepository;
import com.debateia.adapter.out.user.UserRepo;
import com.debateia.domain.League;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class LeagueMapper {

    @Autowired
    protected UserJpaRepository userJpaRepository;

    @Autowired
    protected ParticipationJpaRepository participationJpaRepository;
    
    @Mapping(target = "state", source = "state", qualifiedByName = "mapStateToEnum")
    @Mapping(target = "bots", source = "botIds")
    public abstract LeagueDTO toLeagueResponseDTO(League league);
    
    @Mapping(target = "state", constant = "PENDING")
    @Mapping(target = "leagueId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "matchIds", ignore = true)
    public abstract League toDomain(CreateLeagueDTO dto);
    
    @Mapping(target = "leagueId", source = "id")
    @Mapping(target = "matchIds", source = "matches", qualifiedByName = "mapMatchesToIds")
    @Mapping(target = "botIds", source = "participations", qualifiedByName = "mapParticipationsToIds")
    @Mapping(target = "userId", source = "user.id")
    public abstract League toDomain(LeagueEntity entity);
    
    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUserIdToEntity")
    @Mapping(target = "matches", expression = "java(new ArrayList<>())")
    @Mapping(target = "participations", expression = "java(this.mapBotIdsToParticipations(league.getBotIds(), league.getLeagueId()))")
    @Mapping(target = "id", source = "leagueId")
    public abstract LeagueEntity toEntity(League league);
    
    @Named("mapStateToEnum")
    protected State mapStateToEnum(String state) {
        try {
            return State.valueOf(state);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Named("mapMatchesToIds")
    protected List<Integer> mapMatchesToIds(List<MatchEntity> matches) {
        if (matches == null) return null;
        return matches.stream()
                .map(MatchEntity::getId)
                .collect(Collectors.toList());
    }
    
    @Named("mapParticipationsToIds")
    protected List<Integer> mapParticipationsToIds(List<ParticipationEntity> participations) {
        if (participations == null) return null;
        return participations.stream()
                .map(ParticipationEntity::getBotId)
                .collect(Collectors.toList());
    }
    
    @Named("mapUserIdToEntity")
    protected UserEntity mapUserIdToEntity(Integer userId) {
        if (userId == null) return null;
        return userJpaRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
    }
    
    @Named("mapBotIdsToParticipations")
    protected List<ParticipationEntity> mapBotIdsToParticipations(List<Integer> botIds, Integer leagueId) {
        if (botIds == null) return null;
        return botIds.stream()
                .map(botId -> {
                    return participationJpaRepository.findByLeagueIdAndBotId(leagueId, botId)
                            .orElseThrow(EntityNotFoundException::new);
                })
                .collect(Collectors.toList());
    }
}