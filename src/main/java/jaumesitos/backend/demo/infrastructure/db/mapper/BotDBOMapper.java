package jaumesitos.backend.demo.infrastructure.db.mapper;

import org.mapstruct.Mapper;
import jaumesitos.backend.demo.infrastructure.db.dbo.BotDBO;
import jaumesitos.backend.demo.domain.Bot;

@Mapper(componentModel = "spring")
public interface BotDBOMapper {
    BotDBO toDBO(Bot bot);
    Bot toDomain(BotDBO botDBO);
}
