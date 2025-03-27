package org.example.backend.databaseapi.jpa.bot;

import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.bot.BotId;
import org.example.backend.databaseapi.domain.liga.LigaId;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BotJpaMapper {

    Bot toDomain(BotJpaEntity entity);
    BotJpaEntity toEntity(Bot bot);

    default UsuarioId toUserId(Integer value) {
        return new UsuarioId(value);
    }

    default Integer toInteger(UsuarioId entity) {
        return entity.value();
    }

    default BotId toBotId(Integer value) {
        return new BotId(value);
    }


    default Integer toInteger(BotId entity) {
        return entity.value();
    }

    default LigaId toLigaId(Integer value) {
        return new LigaId(value);
    }

    default Integer toInteger(LigaId entity) {
        return entity.value();
    }

}
