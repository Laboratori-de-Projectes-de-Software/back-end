package com.alia.back_end_service.domain.user.usecases;

import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import com.alia.back_end_service.domain.user.ports.GetAllUserLeaugesPortAPI;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetAllUserLeaguesUseCase implements GetAllUserLeaugesPortAPI {
    private final LeaguePortDB leaguePortDB;

    @Override
    public List<League> GetAllUserLeauges(String username) {
        return leaguePortDB.getLeaguesByUser(username);
    }
}
