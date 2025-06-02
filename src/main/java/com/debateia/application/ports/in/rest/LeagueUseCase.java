package com.debateia.application.ports.in.rest;

import com.debateia.domain.League;
import com.debateia.domain.Participation;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author kjorda
 */
public interface LeagueUseCase {
    // TODO: Integer instead of int?
    
    // Post one league
    League postLeague(League league) throws DataIntegrityViolationException;

    // Get all leagues
    List<League> getAllLeagues(Optional<Integer> ownerId);
    
    // Get one league
    League getLeague(int leagueId);
    
    
    // Update one league
    League updateLeague(int leagueId, int userId, League l);
    
    // Register bot to league
    void registerBot(int leagueId, int botId, int userId) throws DataIntegrityViolationException;
    
    // Get classification from a league
    List<Participation> getScores(int leagueId);
    
    // Delete one league
    League deleteLeague(int leagueId, int userId);
    
    // Start a league (creates all matches)
    void startLeague(int leagueId);
}
