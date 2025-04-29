package com.alia.back_end_service.domain.league.ports;

import com.alia.back_end_service.domain.league.League;

public interface LeagueGetPortAPI {
    League getLeague(Integer id);
}
