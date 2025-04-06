package com.alia.back_end_service.api_rest.message;

import com.alia.back_end_service.api_model.MessageResponseDTO;
import com.alia.back_end_service.domain.message.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapperAPIImpl implements MessageMapperAPI {
    @Override
    public MessageResponseDTO toApiResponse(Message message) {
        MessageResponseDTO messageResponse = new MessageResponseDTO();
        messageResponse.setText(message.getMessage());
        messageResponse.setBotId(message.getBotId());
        messageResponse.setTime(message.getDate());
        return messageResponse;
    }
}
