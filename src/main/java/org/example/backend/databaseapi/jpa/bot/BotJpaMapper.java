package org.example.backend.databaseapi.jpa.bot;

import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.bot.BotId;
import org.example.backend.databaseapi.domain.liga.LigaId;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.example.backend.databaseapi.jpa.usuario.UsuarioJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface BotJpaMapper {

    Bot toDomain(BotJpaEntity entity);

    BotJpaEntity updateBot(BotJpaEntity newBot, @MappingTarget BotJpaEntity actualBot);


    default UsuarioId toUserId(Integer value) {
        return new UsuarioId(value);
    }

    default UsuarioId toUserId(UsuarioJpaEntity value) {
        return new UsuarioId(value.getUserId());
    }

    default Integer toInteger(UsuarioId entity) {
        if(entity==null){
            return null;
        }
        return entity.value();
    }

    default BotId toBotId(Integer value) {
        return new BotId(value);
    }


    default Integer toInteger(BotId entity) {
        if(entity==null){
            return null;
        }
        return entity.value();
    }

    default LigaId toLigaId(Integer value) {
        return new LigaId(value);
    }

    default Integer toInteger(LigaId entity) {
        return entity.value();
    }

}
