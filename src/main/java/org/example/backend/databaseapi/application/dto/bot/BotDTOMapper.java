package org.example.backend.databaseapi.application.dto.bot;

import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.bot.BotId;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface BotDTOMapper {

    @Mapping(target = "urlImagen", source = "imagen")
    @Mapping(target = "name", source = "nombre")
    @Mapping(target = "botId", source = "idBot")
    @Mapping(target = "description", source = "prompt")
    BotDTOResponse toDTOResponse(Bot bot);

    @Mapping(target = "url", source = "endpoint")
    @Mapping(target = "prompt", source = "description")
    @Mapping(target = "nombre", source = "name")
    @Mapping(target = "imagen", source = "urlImagen")
    @Mapping(target = "usuario", source = "userId")
    Bot toBot(BotDTORequest request);

    @Mapping(target = "id", source = "idBot")
    BotSummaryDTOResponse toSummaryDTO(Bot bot);

    default Integer toInteger(BotId botId){
        return botId.value();
    }

    default UsuarioId toUsuarioId(Integer value){
        return new UsuarioId(value);
    }
}
