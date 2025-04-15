package com.debateia.application.ports.in.rest;

import com.debateia.domain.League;
import com.debateia.domain.Participation;
import java.util.List;

/**
 *
 * @author kjorda
 */
public interface LeagueUseCase {
    
    // Post one league
    public League postLeague(League league);

    // Get all leagues
    public List<League> getAllLeagues();
    
    // Get one league
    public League getLeague(int leagueId);
    
    
    // Update one league
    public League updateLeague(int leagueId, League l);
    
    // Register bot to league
    public void registerBot(int leagueId, int botId);
    
    // Get classification from a league
    public List<Participation> getScores(int leagueId);
    
    // Delete one league
    public League deleteLeague(int leagueId, int userId);
    
    // Start a league (creates all matches)
    public void startLeague(int leagueId);
    
    // Get all matches from a league
    public void getAllMatches(int leagueId);
}
