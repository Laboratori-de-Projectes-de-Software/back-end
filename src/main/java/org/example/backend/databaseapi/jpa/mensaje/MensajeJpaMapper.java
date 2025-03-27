package org.example.backend.databaseapi.jpa.mensaje;

import org.example.backend.databaseapi.domain.mensaje.Mensaje;
import org.example.backend.databaseapi.domain.bot.BotId;
import org.example.backend.databaseapi.domain.liga.LigaId;
import org.example.backend.databaseapi.domain.mensaje.MensajeId;
import org.example.backend.databaseapi.domain.partida.PartidaId;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MensajeJpaMapper {

    Mensaje toDomain(MensajeJpaEntity entity);

    MensajeJpaEntity toEntity(Mensaje mensaje);

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

    default MensajeId toMensajeId(Integer value) {
        return new MensajeId(value);
    }

    default Integer toInteger(MensajeId entity) {
        return entity.value();
    }

    default PartidaId toPartidaId(Integer value) {
        return new PartidaId(value);
    }

    default Integer toInteger(PartidaId entity) {
        return entity.value();
    }


}
