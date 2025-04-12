package uib.lab.api.application.port;

import uib.lab.api.domain.MatchDomain;
import java.util.List;
import java.util.Optional;

public interface MatchPort {
    Optional<MatchDomain> findById(int id);
    List<MatchDomain> findAll();
}
