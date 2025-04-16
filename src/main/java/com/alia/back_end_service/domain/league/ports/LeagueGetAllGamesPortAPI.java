package com.alia.back_end_service.domain.league.ports;

import com.alia.back_end_service.domain.game.Game;

import java.util.List;

public interface LeagueGetAllGamesPortAPI {
    List<Game> getAllLeagueGames(Integer league_id);
}
