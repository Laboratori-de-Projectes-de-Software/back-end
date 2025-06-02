package com.debateia.application.ports.out.persistence;

import com.debateia.domain.Participation;
import java.util.List;
import java.util.Optional;


public interface ParticipationRepository {
    Participation save(Participation participation);
    void createParticipation(Integer leagueId, Integer botId);
    Optional<Participation> findById(Integer leagueId, Integer botId);
    List<Participation> findByLeagueId(Integer leagueId);
}
