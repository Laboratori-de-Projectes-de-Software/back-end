package com.alia.back_end_service.domain.league.ports;

import com.alia.back_end_service.domain.league.League;

import java.util.List;

public interface LeagueGetAllByUserPortAPI {
    List<League> getAllLeaguesByUser(String username);
}
