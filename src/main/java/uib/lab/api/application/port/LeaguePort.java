package uib.lab.api.application.port;

import java.util.List;

import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.domain.UserDomain;
import java.util.Optional;

public interface LeaguePort {
    LeagueDomain save(LeagueDomain bot);
    Optional<LeagueDomain> findById(int id);

    List<LeagueDomain> findAllByUser(UserDomain user);
    List<LeagueDomain> findAllLeagues();

    void delete(LeagueDomain league);
}
