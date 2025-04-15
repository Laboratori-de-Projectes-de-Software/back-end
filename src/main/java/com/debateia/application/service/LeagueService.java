package com.debateia.application.service;

import com.debateia.application.ports.in.rest.LeagueUseCase;
import com.debateia.application.ports.out.persistence.LeagueRepository;
import com.debateia.application.ports.out.persistence.MatchRepository;
import com.debateia.application.ports.out.persistence.UserRepository;
import com.debateia.domain.League;
import com.debateia.domain.Participation;
import com.debateia.domain.User;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
    private final UserRepository userRepository;
    
    @Override
    public League postLeague(League l) {
        return leagueRepository.saveLeague(l);
    }
    
    @Override
    public League getLeague(int leagueId) {
        Optional<League> lg = leagueRepository.findById(leagueId);
        if (lg.isEmpty())
            throw new EntityNotFoundException("Liga con ID " + leagueId + " no encontrada");
            
        return leagueRepository.findById(leagueId).get();
    }

    @Override
    public List<League> getAllLeagues(Optional<Integer> ownerId) {
        if (ownerId.isEmpty())
            return leagueRepository.findAll();
        
        Optional<User> user = userRepository.findById(ownerId.get());
        
        if (user.isEmpty()) // Comprobamos que el usuario exista
            throw new EntityNotFoundException("Usuario con ID " + ownerId.get() + " no encontrado");
        
        
        return leagueRepository.findByUserId(ownerId.get());
    }

    @Override
    public League updateLeague(int leagueId, int userId, League l) {
        Optional<League> lg = leagueRepository.findById(leagueId);
        if (lg.isEmpty())
            throw new EntityNotFoundException("Liga con ID " + leagueId + " no encontrada");
        
        if (lg.get().getUserId() != userId)
            throw new DataIntegrityViolationException("La liga con ID "+leagueId+" no te pertenece.");
        
        return leagueRepository.updateLeague(leagueId, l);
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
    public League deleteLeague(int leagueId, int userId) {
        Optional<League> lg = leagueRepository.findById(leagueId);
        if (lg.isEmpty()) 
            throw new EntityNotFoundException("Liga con ID " + leagueId + " no encontrada");
        
        if (lg.get().getUserId() != userId) 
            throw new DataIntegrityViolationException("La liga con ID "+leagueId+" no te pertenece.");
        
        leagueRepository.deleteById(leagueId);
        
        return lg.get();
    }

    @Override
    public void startLeague(int leagueId) {
        Optional<League> league = leagueRepository.findById(leagueId);
        if (league.isEmpty())
            throw new EntityNotFoundException("Liga con ID " + leagueId + " no encontrada");
        
        matchService.createLeagueMatches(league.get());
    }

    @Override
    public void getAllMatches(int leagueId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
