package com.debateia.application.ports.in.rest;

import com.debateia.domain.League;
import com.debateia.domain.Match;
import java.util.List;

/**
 *
 * @author kjorda
 */
public interface MatchUseCase {
    public List<Match> getMatchesByLeagueId(Integer leagueId);
    public List<Match> createLeagueMatches(League league);
    public boolean isMatchFinished(Integer matchId, String token);
    public void finalizeMatch(Integer matchId, String token, Integer botId);
    public Match startMatch(int matchId);
}
