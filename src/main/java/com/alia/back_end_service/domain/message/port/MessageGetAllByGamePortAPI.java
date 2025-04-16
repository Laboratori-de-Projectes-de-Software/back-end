package com.alia.back_end_service.domain.message.port;

import com.alia.back_end_service.domain.message.Message;

import java.util.List;

public interface MessageGetAllByGamePortAPI {
    List<Message> messageGetAllByGame(Integer id);
}
