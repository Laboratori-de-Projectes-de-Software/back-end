package uib.lab.api.application.port;

import uib.lab.api.domain.MatchDomain;
import uib.lab.api.infraestructure.jpaEntity.Match;

import java.util.Optional;

public interface MatchPort {
    Optional<MatchDomain> findById(int id);
}
