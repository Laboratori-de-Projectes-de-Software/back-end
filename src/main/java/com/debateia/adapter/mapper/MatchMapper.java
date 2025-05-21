package com.debateia.adapter.mapper;

import com.debateia.adapter.mapper.BotMapper;
import com.debateia.adapter.in.rest.league.State;
import com.debateia.adapter.in.rest.match.MatchDTO;
import com.debateia.adapter.out.bot.BotEntity;
import com.debateia.adapter.out.league.LeagueEntity;
import com.debateia.adapter.out.match.MatchEntity;
import com.debateia.adapter.out.message.MessageEntity;
import com.debateia.domain.Bot;
import com.debateia.domain.Match;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
    @Mapping(target = "messageIds", source = "messages", qualifiedByName = "mapMessagesToIds")
    @Mapping(target = "fighters",  source = ".", qualifiedByName = "mapBotsToDomain")
    Match toDomain(MatchEntity entity);
    
    @Mapping(target = "state", source = "state", qualifiedByName = "stateToString")
    @Mapping(target = "roundNumber", source = "roundNumber")
    @Mapping(target = "id", source = "matchId")
    @Mapping(target = "messages", expression = "java(new ArrayList<>())")
    @Mapping(target = "bot1", source = "bot1id", qualifiedByName = "mapBotIdToEntity")
    @Mapping(target = "bot2", source = "bot2id", qualifiedByName = "mapBotIdToEntity")
    @Mapping(target = "league", source = "leagueId", qualifiedByName = "mapLeagueIdToEntity")
    MatchEntity toEntity(Match dom);
    
    @Mapping(target = "id", source = "matchId")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "result", source = "result")
    @Mapping(target = "roundNumber", source = "roundNumber")
    MatchDTO toResponseDTO(Match dom);
    
    @Named("mapBotsToDomain")
    default List<Bot> mapBotsToDomain(MatchEntity e) {
        BotEntity botEnt1 = e.getBot1();
        BotEntity botEnt2 = e.getBot2();
        
        Bot bot1 = BotMapper.INSTANCE.EntityToDomain(botEnt1);
        Bot bot2 = BotMapper.INSTANCE.EntityToDomain(botEnt2);
        
        System.out.println(bot2.toString());
        
        return Arrays.asList(bot1, bot2); // Siempre el mismo orden
    }
    
    @Named("mapMessagesToIds")
    default List<Integer> mapMessagesToIds(List<MessageEntity> messages) {
        if (messages == null) return null;
        return messages.stream()
                .map(MessageEntity::getId)
                .collect(Collectors.toList());
    }
    
    
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
    
    @Named("mapBotIdToEntity")
    default BotEntity mapBotIdToEntity(Integer botId) {
        if (botId == null) return null;
        BotEntity dummy = new BotEntity();
        dummy.setId(botId);
        return dummy;
    }
    
    @Named("mapLeagueIdToEntity")
    default LeagueEntity mapLeagueIdToEntity(Integer leagueId) {
        if (leagueId == null) return null;
        LeagueEntity dummy = new LeagueEntity();
        dummy.setId(leagueId);
        return dummy;
    }
}