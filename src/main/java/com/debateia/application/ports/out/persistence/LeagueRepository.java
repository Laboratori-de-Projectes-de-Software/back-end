package com.debateia.application.ports.out.persistence;

import com.debateia.domain.League;

/**
 *
 * @author kjorda
 */
public interface LeagueRepository {
    public League findByLeagueId(int leagueId);
    public League saveLeague(League l);
}
