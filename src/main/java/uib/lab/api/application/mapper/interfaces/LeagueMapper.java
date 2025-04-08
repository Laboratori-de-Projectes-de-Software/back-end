package uib.lab.api.application.mapper.interfaces;

import uib.lab.api.domain.LeagueDomain;
import uib.lab.api.infraestructure.jpaEntity.League;

public interface LeagueMapper {
    //Transformamos de entity a Domain
    LeagueDomain toDomain(League league);

    //Transformamos de Domain a entity
    League toEntity(LeagueDomain league);

    League toEntity(LeagueDomain league, boolean b);
}
