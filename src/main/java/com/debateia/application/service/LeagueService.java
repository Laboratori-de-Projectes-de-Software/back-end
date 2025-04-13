package com.debateia.application.service;

import com.debateia.application.ports.in.rest.LeagueUseCase;
import com.debateia.application.ports.out.persistence.LeagueRepository;
import com.debateia.application.ports.out.persistence.MatchRepository;
import com.debateia.domain.League;
import com.debateia.domain.Participation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Handles matches, combats, scores, etc...
 * @author kjorda
 */

@Service
@RequiredArgsConstructor
public class LeagueService implements LeagueUseCase {
    private final LeagueRepository leagueRepository;
    private final MatchService matchService;
    
    @Override
    public League getLeague(int leagueId) {
        return leagueRepository.findByLeagueId(leagueId);
    }

    @Override
    public List<League> getAllLeagues() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public League updateLeague(int leagueId, League l) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void registerBot(int leagueId, int botId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Participation> getScores(int leagueId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public League deleteLeague(int leagueId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void startLeague(int leagueId) {
        League league = leagueRepository.findByLeagueId(leagueId);
        matchService.createLeagueMatches(league);
    }

    @Override
    public void getAllMatches(int leagueId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
