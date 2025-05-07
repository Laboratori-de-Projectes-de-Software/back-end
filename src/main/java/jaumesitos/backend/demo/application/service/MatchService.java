package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.application.repository.IMatchRepository;
import jaumesitos.backend.demo.domain.Match;
import jaumesitos.backend.demo.infrastructure.db.mapper.MatchDBOMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class MatchService {
    private final IMatchRepository enfrentamentRepository;


    public MatchService(IMatchRepository enfrentamentRepository) {
        this.enfrentamentRepository = enfrentamentRepository;
    }


    public void registerEnfrentament(String idBotLocal, String idBotVisitant, LocalDateTime date, String resultat) {
        Match enfrentament = new Match(UUID.randomUUID().toString(), idBotLocal, idBotVisitant, date, resultat);
        enfrentamentRepository.save(enfrentament);
    }

    public Optional<Match> getMatchById(String id) {
        return enfrentamentRepository.findById(id);
    }

    public List<Match> getAllMatches() {
        return enfrentamentRepository.findAll();
    }

    public List<Match> getMatchesByLeagueId(int leagueId) {
        return enfrentamentRepository.findByLeagueId(leagueId);
    }



}