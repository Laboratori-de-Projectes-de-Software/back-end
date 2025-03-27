package org.example.backend.databaseapi.jpa.liga;

import org.example.backend.databaseapi.domain.liga.Liga;
import org.example.backend.databaseapi.domain.bot.BotId;
import org.example.backend.databaseapi.domain.liga.LigaId;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LigaJpaMapper {

    LigaJpaEntity toEntity(Liga liga);

    Liga toDomain(LigaJpaEntity entity);

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
