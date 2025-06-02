package com.debateia.application.ports.in.rest;

import com.debateia.domain.League;
import com.debateia.domain.Match;
import java.util.List;

/**
 *
 * @author kjorda
 */
public interface MatchUseCase {
    List<Match> getMatchesByLeagueId(Integer leagueId);
    List<Match> createLeagueMatches(League league);
    boolean isMatchFinished(Integer matchId, String token);
    void finalizeMatch(Integer matchId, String token, Integer botId);
    Match getMatchById(Integer matchId);
    Match startMatch(int matchId);
}

