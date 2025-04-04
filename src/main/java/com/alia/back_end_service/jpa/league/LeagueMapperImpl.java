package com.alia.back_end_service.jpa.league;

import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.jpa.bot.BotMapper;
import com.alia.back_end_service.jpa.round.RoundEntity;
import com.alia.back_end_service.jpa.round.RoundMapper;
import com.alia.back_end_service.jpa.bot.BotEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LeagueMapperImpl implements LeagueMapper {

    @Override
    public League toDomain(LeagueEntity entity) {
        if (entity == null) return null;

        League league = new League();
        league.setId(entity.getId());
        league.setName(entity.getName());
        league.setInit_time(entity.getInit_time());
        league.setEnd_time(entity.getEnd_time());
        league.setTime_match(entity.getTime_match());
        league.setNumber_match(entity.getNumber_match());
        league.setState(entity.getState());

        if (entity.getBots() != null) {
            List<Integer> botIds = entity.getBots().stream()
                    .map(BotEntity::getId)
                    .collect(Collectors.toList());
            league.setBotIds(botIds);
        } else {

            league.setBotIds(Collections.emptyList());
        }

        if (entity.getRounds() != null) {
            List<Integer> roundIds = entity.getRounds().stream()
                    .map(RoundEntity::getId)
                    .collect(Collectors.toList());
            league.setRoundIds(roundIds);
        } else {
            league.setRoundIds(Collections.emptyList());
        }

        return league;
    }

    @Override
    public LeagueEntity toEntity(League domain) {
        if (domain == null) return null;

        LeagueEntity entity = new LeagueEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setInit_time(domain.getInit_time());
        entity.setEnd_time(domain.getEnd_time());
        entity.setTime_match(domain.getTime_match());
        entity.setNumber_match(domain.getNumber_match());
        entity.setState(domain.getState());

        return entity;
    }
}
