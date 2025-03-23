package com.alia.back_end_service.domain.league.ports;

import com.alia.back_end_service.domain.league.League;

import java.util.List;
import java.util.Optional;

public interface LeaguePortDB {

    League saveLeague(League league);

    void deleteLeague(Long id); // Para eliminar

    List<League> getAllLeagues();

    List<League> getLeaguesByUser(String username);
}
