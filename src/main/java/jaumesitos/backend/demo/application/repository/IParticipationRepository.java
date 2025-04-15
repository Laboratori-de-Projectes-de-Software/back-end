package jaumesitos.backend.demo.application.repository;

import jaumesitos.backend.demo.domain.Participation;

import java.util.List;

public interface IParticipationRepository {
    void postParticipation(Participation c);
    List<Participation> getParticipations(Integer leagueId);
}
