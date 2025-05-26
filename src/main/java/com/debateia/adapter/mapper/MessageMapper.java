package com.debateia.adapter.mapper;

import com.debateia.adapter.in.rest.bot.BotMessageDTO;
import com.debateia.adapter.in.rest.match.MessageResponseDTO;
import com.debateia.adapter.out.message.MessageEntity;
import com.debateia.domain.Messages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = MessageMapperHelper.class)
public interface MessageMapper {

    @Mapping(target = "botId", source = "bot.id")
    @Mapping(target = "contents", source = "text")
    @Mapping(target = "timestamp", source = "time")
    @Mapping(target = "matchId", source = "match.id")
    Messages toDomain(MessageEntity entity);

    @Mapping(target = "contents", source = "text")
    @Mapping(target = "timestamp", source = "time", qualifiedByName = "stringToLocalDateTime")
    Messages toDomain(BotMessageDTO dto);

    @Mapping(target = "text", source = "contents")
    @Mapping(target = "time", source = "timestamp", qualifiedByName = "localDateTimeToString")
    BotMessageDTO toBotMessage(Messages message);

    @Mapping(target = "text", source = "contents")
  
    @Mapping(target = "time", expression = "java(dom.getTimestamp().toString())")
    MessageResponseDTO toResponseDTO(Messages dom);

    @Mapping(target = "bot", source = "botId", qualifiedByName = "mapBotIdToEntity")
    @Mapping(target = "match", source = "matchId", qualifiedByName = "mapMatchIdToEntity")
    @Mapping(target = "text", source = "contents")
    @Mapping(target = "time", source = "timestamp")
    MessageEntity toEntity(Messages messages);

    @Named("stringToLocalDateTime")
    default LocalDateTime stringToLocalDateTime(String timestamp) {
        return LocalDateTime.parse(timestamp);
    }

    @Named("localDateTimeToString")
    default String localDateTimeToString(LocalDateTime timestamp) {
        return timestamp.toString();
    }
}