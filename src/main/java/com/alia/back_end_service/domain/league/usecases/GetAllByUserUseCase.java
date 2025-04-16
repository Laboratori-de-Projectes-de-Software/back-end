package com.alia.back_end_service.domain.league.usecases;

import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import com.alia.back_end_service.domain.league.ports.LeagueGetAllByUserPortAPI;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetAllByUserUseCase implements LeagueGetAllByUserPortAPI {
    private final LeaguePortDB leaguePortDB;

    @Override
    public List<League> getAllLeaguesByUser(String username) {
        return leaguePortDB.findLeaguesByOwner_Username(username);
    }
}
