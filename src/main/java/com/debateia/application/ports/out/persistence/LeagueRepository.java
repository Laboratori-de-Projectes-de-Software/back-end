package com.debateia.application.ports.out.persistence;

import com.debateia.domain.League;

/**
 *
 * @author kjorda
 */
public interface LeagueRepository {
    public League findById(int leagueId);
    public League saveLeague(League l);
    public void deleteById(int leagueId);
}
