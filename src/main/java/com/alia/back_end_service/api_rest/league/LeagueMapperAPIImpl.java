package com.alia.back_end_service.api_rest.league;

import com.alia.back_end_service.api_model.LeagueCreate;
import com.alia.back_end_service.api_model.LeagueResponse;
import com.alia.back_end_service.api_model.LeagueUpdate;
import com.alia.back_end_service.domain.league.League;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class LeagueMapperAPIImpl implements LeagueMapperAPI {
    @Override
    public League toDomainCreate(LeagueCreate leagueCreate) {
        League league = new League();
        league.setName(leagueCreate.getName());
        league.setNumber_match(leagueCreate.getNumberMatch());
        league.setTime_match(leagueCreate.getTimeMatch());
        league.setInit_time(OffsetDateTime.now());
        league.setState("Espera"); // Crear enum
        return league;
    }

    @Override
    public League toDomainUpdate(LeagueUpdate leagueUpdate, Integer id) {
        League league = new League();
        league.setId(id);
        league.setName(leagueUpdate.getName());
        league.setNumber_match(leagueUpdate.getNumberMatch());
        league.setTime_match(leagueUpdate.getTimeMatch());
        league.setInit_time(leagueUpdate.getInitTime());
        league.setEnd_time(leagueUpdate.getEndTime());
        league.setState(leagueUpdate.getState()); // Crear enum
        return league;
    }

    @Override
    public LeagueResponse toApiResponse(League league) {
        LeagueResponse leagueResponse = new LeagueResponse();
        leagueResponse.setId(league.getId());
        leagueResponse.setName(league.getName());
        leagueResponse.setNumberMatch(league.getNumber_match());
        leagueResponse.setTimeMatch(league.getTime_match());
        leagueResponse.setInitTime(league.getInit_time());
        leagueResponse.setEndTime(league.getEnd_time());
        leagueResponse.setState(league.getState());
        return leagueResponse;
    }
}
