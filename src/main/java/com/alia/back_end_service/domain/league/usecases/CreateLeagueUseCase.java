package com.alia.back_end_service.domain.league.usecases;

import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.domain.league.ports.LeagueCreatePortAPI;
import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateLeagueUseCase implements LeagueCreatePortAPI {
    private final LeaguePortDB leaguePortDB;

    @Override
    public League createLeague(League league) {

        return leaguePortDB.saveLeague(league);
    }
}
