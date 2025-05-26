package com.debateia.adapter.mapper;

import com.debateia.adapter.in.rest.match.MessageDTO;
import com.debateia.adapter.out.message.MessageEntity;
import com.debateia.domain.Messages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);
    
    @Mapping(target = "botId", source = "bot.id")
    @Mapping(target = "contents", source = "text")
    @Mapping(target = "timestamp", source = "time")
    @Mapping(target = "matchId", source = "match.id")
    Messages toDomain(MessageEntity entity);
    
    @Mapping(target = "text", source = "contents")
    @Mapping(target = "timestamp", expression = "java(dom.getTimestamp().toString())")
    MessageDTO toResponseDTO(Messages dom);
}