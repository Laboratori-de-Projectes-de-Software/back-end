package uib.lab.api.application.mapper.interfaces;

import uib.lab.api.domain.RoundDomain;
import uib.lab.api.infraestructure.jpaEntity.Round;

public interface RoundMapper {    
    RoundDomain toDomain(Round round);
    Round toEntity(RoundDomain round);
}
