package org.example.backend.databaseapi.application.dto.mensaje;

import org.example.backend.databaseapi.domain.bot.BotId;
import org.example.backend.databaseapi.domain.mensaje.Mensaje;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.sql.Timestamp;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface MensajeDTOMapper {

    @Mapping(target = "time", source = "hora")
    @Mapping(target = "text", source = "texto")
    @Mapping(target = "botId", source = "bot")
    MessageDTOResponse toMessageDTOResponse(Mensaje mensaje);

    default Integer toInteger(BotId botId){
        return botId.value();
    }

    default String toString(Timestamp timestamp){
        return timestamp.toString();
    }
}
