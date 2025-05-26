package com.debateia.adapter.mapper;

import com.debateia.adapter.in.rest.auth.AuthenticatedUserDTO;
import com.debateia.adapter.out.bot.BotEntity;
import com.debateia.adapter.out.league.LeagueEntity;
import com.debateia.adapter.out.user.UserEntity;
import com.debateia.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    
    @Mapping(target = "id", source = "userId")
    @Mapping(target = "league", source = "leagueId", qualifiedByName = "leagueIdToEntity")
    @Mapping(target = "bots", source = "botsId", qualifiedByName = "botIdsToEntities")
    UserEntity toEntity(User user);
    
    @Mapping(target = "userId", source = "id")
    @Mapping(target = "leagueId", source = "league.id")
    @Mapping(target = "botsId", ignore = true) // <- Maybe should implement it but we don't use it so ¯\_(ツ)_/¯
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "expiresIn", ignore = true)
    User entityToDomain(UserEntity entity);
    
    @Mapping(target = "user", source = "username")
    @Mapping(target = "token", source = "token")
    AuthenticatedUserDTO toResponseDTO(User user);
    
    @Named("leagueIdToEntity")
    default LeagueEntity leagueIdToEntity(Integer leagueId) {
        if (leagueId == null) return null;
        LeagueEntity dummyL = new LeagueEntity();
        dummyL.setId(leagueId);
        return dummyL;
    }
    
    @Named("botIdsToEntities")
    default List<BotEntity> botIdsToEntities(List<Integer> botIds) {
        if (botIds == null) return null;
        return botIds.stream()
                .map(id -> {
                    BotEntity botEntity = new BotEntity();
                    botEntity.setId(id);
                    return botEntity;
                })
                .collect(Collectors.toList());
    }
}