package com.developers.iasuperleague.application.port.out;


import com.developers.iasuperleague.domain.model.League;

import java.util.List;

/**
 * Define las operaciones para guardar y obtener ligas.
 */
public interface LeagueRepository {
    League save(League league);
    League findById(Long leagueId);
    List<League> findAll();
}
