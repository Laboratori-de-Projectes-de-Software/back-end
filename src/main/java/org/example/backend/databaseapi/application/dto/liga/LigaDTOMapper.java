package org.example.backend.databaseapi.application.dto.liga;

import org.example.backend.databaseapi.domain.bot.BotId;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface LigaDTOMapper {

    @Mapping(target = "name", source = "nombre")
    @Mapping(target = "user", source = "usuario")
    @Mapping(target = "leagueId", source = "ligaId")
    @Mapping(target = "bots", source = "botsLiga")
    LeagueDTOResponse toLeagueDTOResponse(Liga liga);

    @Mapping(target = "usuario", source = "userId")
    @Mapping(target = "nombre", source = "name")
    @Mapping(target = "botsLiga", source = "bots")
    Liga toLiga(LeagueDTORequest request);

    default Integer toInteger(UsuarioId id){
        return id.value();
    }

    default Integer toInteger(BotId id){
        return id.value();
    }

    default BotId toBotId(Integer value){
        return new BotId(value);
    }

    default UsuarioId toUsuarioId(Integer value){
        return new UsuarioId(value);
    }
}
