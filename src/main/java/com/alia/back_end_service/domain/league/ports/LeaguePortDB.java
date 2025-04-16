package com.alia.back_end_service.domain.league.ports;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.game.Game;
import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.jpa.league.LeagueEntity;

import java.util.List;
import java.util.Optional;

public interface LeaguePortDB {

    League saveLeague(League league);

    League getLeague(Integer id);

    League inscribeBot(Integer league_id, Integer bot_id);
    League updateLeague(League league);
    List<Bot> getAllLeagueBots(Integer leagueId);

    void deleteLeague(Integer id); // Para eliminar

    List<League> getAllLeagues();

    List<League> getLeaguesByUser(String username);

    List<League> getLeaguesByBotId(Integer botId);

    boolean existLeagueIdWithBotId(Integer leagueId, Integer botId);

    List<Game> getAllLeagueGamesByLeagueId(Integer leagueId);

    List<League> findLeaguesByOwner_Username(String ownerUsername);
}
