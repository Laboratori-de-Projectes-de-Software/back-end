package com.alia.back_end_service.domain.league.usecases;

import com.alia.back_end_service.domain.league.League;
import com.alia.back_end_service.domain.league.ports.LeagueInscribeBotPortAPI;
import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InscribeBotUseCase implements LeagueInscribeBotPortAPI {
    private final LeaguePortDB leaguePortDB;

    @Override
    public League inscribe(Integer league_id, String bot_name) {
        return leaguePortDB.inscribeBot(league_id, bot_name);
    }
}
