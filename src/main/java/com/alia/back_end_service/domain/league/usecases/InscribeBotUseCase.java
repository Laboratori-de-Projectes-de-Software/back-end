package com.alia.back_end_service.domain.league.usecases;

import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.domain.league.exceptions.BotAlreadyRegisteredInALeague;
import com.alia.back_end_service.domain.league.ports.LeagueInscribeBotPortAPI;
import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InscribeBotUseCase implements LeagueInscribeBotPortAPI {
    private final LeaguePortDB leaguePortDB;

    @Override
    public League inscribe(Integer league_id, Integer bot_id) {
        if(leaguePortDB.existLeagueIdWithBotId(league_id, bot_id))throw new BotAlreadyRegisteredInALeague("El bot ya está inscrito en una liga activa o en espera.");
        return leaguePortDB.inscribeBot(league_id, bot_id);
    }
}
