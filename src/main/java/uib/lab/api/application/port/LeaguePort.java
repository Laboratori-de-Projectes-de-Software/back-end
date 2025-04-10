package uib.lab.api.application.port;

import java.util.List;

import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.domain.UserDomain;

public interface LeaguePort {
    LeagueDomain save(LeagueDomain bot);

    List<LeagueDomain> findAllByUser(UserDomain user);
    List<LeagueDomain> findAllLeagues();
}
