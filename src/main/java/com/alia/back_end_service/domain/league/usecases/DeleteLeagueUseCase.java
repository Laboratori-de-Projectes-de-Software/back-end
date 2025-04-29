package com.alia.back_end_service.domain.league.usecases;

import com.alia.back_end_service.domain.league.ports.LeagueDeletePortAPI;
import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteLeagueUseCase implements LeagueDeletePortAPI {

    private final LeaguePortDB leaguePortDB;
    @Override
    public void deleteLeague(Integer leagueId) {
        leaguePortDB.deleteLeague(leagueId);
    }
}
