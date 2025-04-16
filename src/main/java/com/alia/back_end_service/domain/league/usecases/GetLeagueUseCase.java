package com.alia.back_end_service.domain.league.usecases;

import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.domain.league.ports.LeagueGetPortAPI;
import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetLeagueUseCase implements LeagueGetPortAPI {
    private final LeaguePortDB portDB;

    @Override
    public League getLeague(Integer id) {
        return portDB.getLeague(id);
    }
}
