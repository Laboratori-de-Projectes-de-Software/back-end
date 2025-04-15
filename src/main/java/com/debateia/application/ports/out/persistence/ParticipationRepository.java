package com.debateia.application.ports.out.persistence;

import com.debateia.domain.Participation;
import java.util.Optional;


public interface ParticipationRepository {
    public void createParticipation(Integer leagueId, Integer botId);
    public Optional<Participation> findById(Integer leagueId, Integer botId);
}
