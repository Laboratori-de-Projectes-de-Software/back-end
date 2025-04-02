package com.alia.back_end_service.jpa.message;

import com.alia.back_end_service.domain.message.Message;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

public class MessageMapperImpl implements MessageMapper {
    @Override
    public Message toDomain(MessageEntity entity) {
        if (entity == null) {
            return null;
        }

        Message domain = new Message();
        domain.setId(entity.getId());
        domain.setMessage(entity.getMessage());

        // Convertir el campo "date" de String a OffsetDateTime
        try {
            domain.setDate(OffsetDateTime.parse(entity.getDate()));
        } catch (DateTimeParseException e) {
            // Puedes manejar el error como prefieras; aquí asignamos null
            domain.setDate(null);
        }

        // Extraer el identificador del Bot (asumimos que BotEntity.getName() es el ID)
        domain.setBotId(entity.getBot() != null ? entity.getBot().getName() : null);

        // Extraer el ID del Game
        domain.setGameId(entity.getGame() != null ? entity.getGame().getId() : null);

        return domain;
    }

    @Override
    public MessageEntity toEntity(Message domain) {
        if (domain == null) {
            return null;
        }

        MessageEntity entity = new MessageEntity();
        entity.setId(domain.getId());
        entity.setMessage(domain.getMessage());

        // Convertir OffsetDateTime a String en formato ISO-8601
        entity.setDate(domain.getDate() != null ? domain.getDate().toString() : null);

        // Las relaciones (bot y game) se asignan en otro nivel (por ejemplo, en el adaptador)
        return entity;
    }
}
