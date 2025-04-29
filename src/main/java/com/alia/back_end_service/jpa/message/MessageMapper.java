package com.alia.back_end_service.jpa.message;

import com.alia.back_end_service.domain.message.Message;

public interface MessageMapper {
    Message toDomain(MessageEntity entity);
    MessageEntity toEntity(Message domain);
}
