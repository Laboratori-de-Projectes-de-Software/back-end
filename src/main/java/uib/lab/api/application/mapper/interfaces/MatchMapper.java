package uib.lab.api.application.mapper.interfaces;

import uib.lab.api.domain.MatchDomain;
import uib.lab.api.infraestructure.jpaEntity.Match;

public interface MatchMapper {
    MatchDomain toDomain(Match match);
    Match toEntity(MatchDomain match);
}
