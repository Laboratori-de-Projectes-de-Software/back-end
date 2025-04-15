package com.debateia.application.ports.out.persistence;

import com.debateia.domain.League;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kjorda
 */
public interface LeagueRepository {
    public Optional<League> findById(Integer leagueId);
    public League saveLeague(League l);
    public void deleteById(Integer leagueId);
    public League updateLeague(Integer leagueId, League l);
    List<League> findAll();
    List<League> findByUserId(Integer userId);
}
