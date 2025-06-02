package com.debateia.adapter.mapper;

import com.debateia.adapter.in.rest.bot.BotMessageDTO;
import com.debateia.adapter.in.rest.match.MessageDTO;
import com.debateia.adapter.out.bot.BotEntity;
import com.debateia.adapter.out.bot.BotJpaRepository;
import com.debateia.adapter.out.match.MatchEntity;
import com.debateia.adapter.out.match.MatchJpaRepository;
import com.debateia.adapter.out.message.MessageEntity;
import com.debateia.domain.Messages;
import jakarta.persistence.EntityExistsException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {

    @Autowired
    private BotJpaRepository botRepository;

    @Autowired
    private MatchJpaRepository matchRepository;

    @Mapping(target = "botId", source = "bot.id")
    @Mapping(target = "contents", source = "text")
    @Mapping(target = "timestamp", source = "time")
    @Mapping(target = "matchId", source = "match.id")
    public abstract Messages toDomain(MessageEntity entity);

    @Mapping(target = "contents", source = "text")
    @Mapping(target = "timestamp", source = "time", qualifiedByName = "stringToLocalDateTime")
    public abstract Messages toDomain(BotMessageDTO dto);

    @Mapping(target = "text", source = "contents")
    @Mapping(target = "time", source = "timestamp", qualifiedByName = "localDateTimeToString")
    public abstract BotMessageDTO toBotMessage(Messages message);

    @Mapping(target = "text", source = "contents")
  
    @Mapping(target = "timestamp", expression = "java(dom.getTimestamp().toString())")
    public abstract MessageDTO toResponseDTO(Messages dom);

    @Mapping(target = "bot", source = "botId", qualifiedByName = "mapBotIdToEntity")
    @Mapping(target = "match", source = "matchId", qualifiedByName = "mapMatchIdToEntity")
    @Mapping(target = "text", source = "contents")
    @Mapping(target = "time", source = "timestamp")
    public abstract MessageEntity toEntity(Messages messages);

    @Named("stringToLocalDateTime")
    public LocalDateTime stringToLocalDateTime(String timestamp) {
        return LocalDateTime.parse(timestamp);
    }

    @Named("localDateTimeToString")
    public String localDateTimeToString(LocalDateTime timestamp) {
        return timestamp.toString();
    }

    @Named("mapBotIdToEntity")
    public BotEntity mapBotIdToEntity(Integer botId) {
        if (botId == null) return null;

        return botRepository.findById(botId)
                .orElseThrow(EntityExistsException::new);
    }

    @Named("mapMatchIdToEntity")
    public MatchEntity mapMatchIdToEntity(Integer matchId) {
        if (matchId == null) return null;

        return matchRepository.findById(matchId)
                .orElseThrow(EntityExistsException::new);
    }
}