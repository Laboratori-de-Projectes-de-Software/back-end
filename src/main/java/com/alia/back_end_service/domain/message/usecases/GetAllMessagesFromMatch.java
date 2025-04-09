package com.alia.back_end_service.domain.message.usecases;

import com.alia.back_end_service.domain.message.Message;
import com.alia.back_end_service.domain.message.port.MessageGetAllByGamePortAPI;
import com.alia.back_end_service.domain.message.port.MessagePortDB;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetAllMessagesFromMatch implements MessageGetAllByGamePortAPI {
    private final MessagePortDB messagePortDB;
    @Override
    public List<Message> messageGetAllByGame(Integer id) {
        return List.of();
    }
}
