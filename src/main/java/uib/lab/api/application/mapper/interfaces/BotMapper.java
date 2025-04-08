package uib.lab.api.application.mapper.interfaces;

import uib.lab.api.domain.BotDomain;
import uib.lab.api.infraestructure.jpaEntity.Bot;

public interface BotMapper {
    //Transformamos de entity a Domain
    BotDomain toDomain(Bot bot);

    //Transformamos de Domain a entity
    Bot toEntity(BotDomain bot);

    Bot toEntity(BotDomain bot, boolean b);
}