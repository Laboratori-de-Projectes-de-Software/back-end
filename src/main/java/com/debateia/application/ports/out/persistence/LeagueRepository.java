package com.debateia.application.ports.out.persistence;

import com.debateia.adapter.out.persistence.entities.LeagueEntity;

/**
 *
 * @author kjorda
 */
public interface LeagueRepository {
    public LeagueEntity findByLeagueId(Integer leagueId);
}
