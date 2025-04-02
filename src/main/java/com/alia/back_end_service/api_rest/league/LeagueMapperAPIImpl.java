package com.alia.back_end_service.api_rest.league;

import com.alia.back_end_service.api_model.LeagueDTO;
import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.league.League;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@Component
public class LeagueMapperAPIImpl implements LeagueMapperAPI {
    @Override
    public League toDomainCreate(LeagueDTO leagueCreate) {
        League league = new League();
        league.setName(leagueCreate.getName());
        league.setNumber_match(leagueCreate.getRounds());
        league.setBotIds(leagueCreate.getBots());
        league.setTime_match(leagueCreate.getMatchTime());
        league.setInit_time(OffsetDateTime.now());
        league.setState("Espera"); // Crear enum
        return league;
    }


    @Override
    public LeagueDTO toApiResponse(League league) {
        LeagueDTO leagueResponse = new LeagueDTO();
        leagueResponse.setName(league.getName());
        leagueResponse.setRounds(league.getNumber_match());
        leagueResponse.setMatchTime(league.getTime_match());
        league.setBotIds(league.getBotIds());
        return leagueResponse;
    }
}
