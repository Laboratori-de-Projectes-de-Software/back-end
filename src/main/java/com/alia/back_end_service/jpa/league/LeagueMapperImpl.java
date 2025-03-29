package com.alia.back_end_service.jpa.league;

import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.jpa.bot.BotMapper;
import com.alia.back_end_service.jpa.round.RoundEntity;
import com.alia.back_end_service.jpa.round.RoundMapper;
import com.alia.back_end_service.jpa.bot.BotEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LeagueMapperImpl implements LeagueMapper {

    private final BotMapper botMapper;

    public LeagueMapperImpl(BotMapper botMapper) {
        this.botMapper = botMapper;
    }

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

        // Map bots (many-to-many)
        if (entity.getBots() != null) {
            league.setBots(new ArrayList<>(entity.getBots().stream()
                    .map(botMapper::toDomain)
                    .toList()));
        }

        // No mapeamos rounds aún

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

        // Map bots (many-to-many)
        if (domain.getBots() != null) {
            List<BotEntity> bots = domain.getBots().stream()
                    .map(botMapper::toEntity)
                    .toList();
            entity.setBots(bots);
        }

        // No mapeamos rounds aún

        return entity;
    }
}
