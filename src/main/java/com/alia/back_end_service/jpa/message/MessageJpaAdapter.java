package com.alia.back_end_service.jpa.message;

import com.alia.back_end_service.domain.message.Message;
import com.alia.back_end_service.domain.message.port.MessagePortDB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class MessageJpaAdapter implements MessagePortDB {

    private MessageJpaRepository messageJpaRepository;
    private MessageMapper messageMapper;
    @Override
    public List<Message> messageGetAllByGame(Integer id) {
        return messageJpaRepository.getAllMessagesByGameId(id).stream().map(messageMapper::toDomain).toList();
    }
}
