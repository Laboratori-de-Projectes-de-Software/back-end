package org.example.backend.databaseapi.jpa.liga;

import org.example.backend.databaseapi.domain.liga.Liga;
import org.example.backend.databaseapi.domain.bot.BotId;
import org.example.backend.databaseapi.domain.liga.LigaId;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.example.backend.databaseapi.jpa.bot.BotJpaEntity;
import org.example.backend.databaseapi.jpa.usuario.UsuarioJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LigaJpaMapper {


    Liga toDomain(LigaJpaEntity entity);

    default UsuarioId toUserId(Integer value) {
        return new UsuarioId(value);
    }

    default Integer toInteger(UsuarioId entity) {
        return entity.value();
    }

    default UsuarioId toUserId(UsuarioJpaEntity entity) {
        return new UsuarioId(entity.getUserId());
    }

    default BotId toBotId(Integer value) {
        return new BotId(value);
    }

    default BotId toBotId(BotJpaEntity entity) {
        return new BotId(entity.getIdBot());
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
