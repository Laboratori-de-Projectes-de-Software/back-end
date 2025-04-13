package com.debateia.application.ports.out.persistence;

import com.debateia.domain.Match;

import java.util.List;

public interface MatchRepository {
    List<Match> findByLeagueId(Integer leagueId);
    List<Match> saveAll(List<Match> matches);
}
