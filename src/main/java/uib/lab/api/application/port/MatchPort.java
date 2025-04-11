package uib.lab.api.application.port;

import uib.lab.api.infraestructure.jpaEntity.Match;

import java.util.List;
import java.util.Optional;

public interface MatchPort {
    Optional<Match> findById(int id);

    List<Match> saveAll(List<Match> matches);

}
