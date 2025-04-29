package com.alia.back_end_service.domain.league.usecases;

import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import com.alia.back_end_service.domain.league.ports.LeagueUpdatePortAPI;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateLeagueUseCase implements LeagueUpdatePortAPI {
    private final LeaguePortDB leaguePortDB;

    @Override
    public League updateLeague(League league) {
        return leaguePortDB.updateLeague(league);
    }
}
