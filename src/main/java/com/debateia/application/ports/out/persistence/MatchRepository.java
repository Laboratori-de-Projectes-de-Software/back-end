package com.debateia.application.ports.out.persistence;

import com.debateia.domain.Match;

import java.util.List;
import java.util.Optional;

public interface MatchRepository {
    List<Match> findByLeagueId(Integer leagueId);
    List<Match> saveAll(List<Match> matches);
    Optional<Match> findById(Integer matchId);
}
