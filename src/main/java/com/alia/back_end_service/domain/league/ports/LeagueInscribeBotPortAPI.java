package com.alia.back_end_service.domain.league.ports;

import com.alia.back_end_service.domain.league.League;

public interface LeagueInscribeBotPortAPI {
    League inscribe(Integer league_id, Integer bot_id);
}
