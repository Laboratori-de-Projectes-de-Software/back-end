package org.example.backend.databaseapi.jpa.partida;

import org.example.backend.databaseapi.domain.mensaje.MensajeId;
import org.example.backend.databaseapi.domain.partida.Partida;
import org.example.backend.databaseapi.domain.bot.BotId;
import org.example.backend.databaseapi.domain.liga.LigaId;
import org.example.backend.databaseapi.domain.partida.PartidaId;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.example.backend.databaseapi.jpa.liga.LigaJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartidaJpaMapper {

    Partida toDomain(PartidaJpaEntity entity);

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

    default LigaId toLigaId(LigaJpaEntity entity) {
        return new LigaId(entity.getLigaId());
    }

    default Integer toInteger(LigaId entity) {
        return entity.value();
    }

    default PartidaId toPartidaId(Integer value) {
        return new PartidaId(value);
    }

    default Integer toInteger(PartidaId entity) {
        return entity.value();
    }

}
