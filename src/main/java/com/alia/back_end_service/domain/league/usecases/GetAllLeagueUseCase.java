package com.alia.back_end_service.domain.league.usecases;

import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.domain.league.ports.LeagueGetAllPortAPI;
import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetAllLeagueUseCase implements LeagueGetAllPortAPI {
    private final LeaguePortDB portDB;

    @Override
    public List<League> getAllLeagues() {
        return portDB.getAllLeagues();
    }
}
