package com.debateia.application.mapper;

import com.debateia.adapter.in.web.dto.response.MessageResponseDTO;
import com.debateia.adapter.out.persistence.entities.MessageEntity;
import com.debateia.domain.Messages;

public class MessageMapper {
    public static Messages toDomain(MessageEntity entity) {
        Messages dom = new Messages();
        dom.setBotId(entity.getBot().getId());
        dom.setContents(entity.getText());
        dom.setTimestamp(entity.getTime());
        dom.setMatchId(entity.getMatch().getId());
        return dom;
    }

    public static MessageResponseDTO toResponseDTO(Messages dom) {
        MessageResponseDTO dto = new MessageResponseDTO();
        dto.setBotId(dom.getBotId());
        dto.setText(dom.getContents());
        dto.setTime(dom.getTimestamp().toString());
        return dto;
    }
}
