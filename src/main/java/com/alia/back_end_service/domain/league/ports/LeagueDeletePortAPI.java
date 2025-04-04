package com.alia.back_end_service.domain.league.ports;

import com.alia.back_end_service.jpa.league.LeagueEntity;

public interface LeagueDeletePortAPI {
    void deleteLeague(Integer leagueId);
}
