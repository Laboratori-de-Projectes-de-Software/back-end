package uib.lab.api.application.port;

import uib.lab.api.infraestructure.jpaEntity.Match;

import java.util.Optional;

public interface MatchPort {
    Optional<Match> findById(int id);
}
