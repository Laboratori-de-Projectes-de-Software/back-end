package com.alia.back_end_service.domain.league.usecases;

import com.alia.back_end_service.domain.game.Game;
import com.alia.back_end_service.domain.league.ports.LeagueGetAllGamesPortAPI;
import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetAllLeagueGamesUseCase implements LeagueGetAllGamesPortAPI {
    private final LeaguePortDB portDB;

    @Override
    public List<Game> getAllLeagueGames(Integer league_id) {
        return portDB.getAllLeagueGamesByLeagueId(league_id);
    }
}
