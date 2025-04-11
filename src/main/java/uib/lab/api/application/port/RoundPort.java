package uib.lab.api.application.port;

import uib.lab.api.infraestructure.jpaEntity.Round;

public interface RoundPort {
    Round save(Round round);
}