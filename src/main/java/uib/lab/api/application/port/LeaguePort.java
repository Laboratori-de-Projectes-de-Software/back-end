package uib.lab.api.application.port;

import uib.lab.api.domain.LeagueDomain;

public interface LeaguePort {
    LeagueDomain save(LeagueDomain bot);
}
