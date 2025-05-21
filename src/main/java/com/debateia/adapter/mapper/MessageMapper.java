package com.debateia.adapter.mapper;

import com.debateia.adapter.in.rest.bot.BotMessageDTO;
import com.debateia.adapter.in.rest.match.MessageResponseDTO;
import com.debateia.adapter.out.bot.BotEntity;
import com.debateia.adapter.out.match.MatchEntity;
import com.debateia.adapter.out.message.MessageEntity;
import com.debateia.domain.Messages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "botId", source = "bot.id")
    @Mapping(target = "contents", source = "text")
    @Mapping(target = "timestamp", source = "time")
    @Mapping(target = "matchId", source = "match.id")
    Messages toDomain(MessageEntity entity);

    @Mapping(target = "contents", source = "message")
    @Mapping(target = "timestamp", source = "timestamp", qualifiedByName = "stringToLocalDateTime")
    Messages toDomain(BotMessageDTO dto);

    @Mapping(target = "contents", source = "generatedMessage")
    @Mapping(target = "timestamp", expression = "java(LocalDateTime.now())")
    @Mapping(target = "botId", source = "botId")
    @Mapping(target = "matchId", source = "matchId")
    Messages toDomain(GeneratedMessageDTO dto);

    @Mapping(target = "message", source = "contents")
    @Mapping(target = "timestamp", source = "timestamp", qualifiedByName = "localDateTimeToString")
    BotMessageDTO toBotMessage(Messages message);

    @Mapping(target = "text", source = "contents")
    @Mapping(target = "time", expression = "java(dom.getTimestamp().toString())")
    MessageResponseDTO toResponseDTO(Messages dom);

    @Mapping(target = "bot", source = "botId", qualifiedByName = "mapBotIdToEntity")
    @Mapping(target = "match", source = "matchId", qualifiedByName = "mapMatchIdToEntity")
    @Mapping(target = "text", source = "contents")
    @Mapping(target = "time", source = "timestamp")
    MessageEntity toEntity(Messages messages);

    @Named("mapBotIdToEntity")
    default BotEntity mapBotIdToEntity(Integer botId) {
        if (botId == null) return null;
        BotEntity bot = new BotEntity();
        bot.setId(botId);
        return bot;
    }

    @Named("mapMatchIdToEntity")
    default MatchEntity mapMatchIdToEntity(Integer matchId) {
        if (matchId == null) return null;
        MatchEntity match = new MatchEntity();
        match.setId(matchId);
        return match;
    }

    @Named("stringToLocalDateTime")
    default LocalDateTime stringToLocalDateTime(String timestamp) {
        return LocalDateTime.parse(timestamp);
    }

    @Named("localDateTimeToString")
    default String localDateTimeToString(LocalDateTime timestamp) {
        return timestamp.toString();
    }
}