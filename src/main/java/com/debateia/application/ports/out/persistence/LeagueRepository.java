package com.debateia.application.ports.out.persistence;

import com.debateia.domain.League;
import java.util.List;
import java.util.Optional;

public interface LeagueRepository {
    Optional<League> findById(Integer leagueId);
    League saveLeague(League l);
    void deleteById(Integer leagueId);
    League updateLeague(Integer leagueId, League l);
    List<League> findAll();
    List<League> findByUserId(Integer userId);
}
