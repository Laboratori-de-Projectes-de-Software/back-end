package jaumesitos.backend.demo.application.repository;

import jaumesitos.backend.demo.domain.Match;

import java.util.List;
import java.util.Optional;

public interface IMatchRepository {
    void save(Match enfrentament);
    Optional<Match> findById(String id);
    List<Match> findAll();
    List<Match> findByLeagueId(int leagueId);

}
