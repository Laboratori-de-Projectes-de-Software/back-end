package org.example.backend.databaseapi.jpa.bot;

import org.example.backend.databaseapi.domain.Bot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BotJpaMapper {

    Bot toDomain(BotJpaEntity entity);
    BotJpaEntity toEntity(Bot bot);

}
