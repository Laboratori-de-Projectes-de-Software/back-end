package com.alia.back_end_service.api_rest.league;

import com.alia.back_end_service.api_model.LeagueDTO;
import com.alia.back_end_service.api_model.LeagueResponseDTO;
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
        league.setInit_time(null);
        league.setState("Espera"); // Crear enum
        league.setOwner(leagueCreate.getUserId());
        return league;
    }


    @Override
    public LeagueResponseDTO toApiResponse(League league) {
        LeagueResponseDTO leagueResponse = new LeagueResponseDTO();
        leagueResponse.setLeagueId(league.getId());
        leagueResponse.setName(league.getName());
        leagueResponse.setRounds(league.getNumber_match());
        leagueResponse.setMatchTime(league.getTime_match());
        leagueResponse.setBots(league.getBotIds());
        leagueResponse.setUser(league.getOwner());
        return leagueResponse;
    }
}
