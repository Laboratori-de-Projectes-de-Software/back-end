package com.alia.back_end_service.api_rest.message;

import com.alia.back_end_service.api_model.MessageResponse;
import com.alia.back_end_service.domain.message.Message;
import com.alia.back_end_service.jpa.message.MessageMapper;
import org.springframework.stereotype.Component;

@Component
public class MessageMapperAPIImpl implements MessageMapperAPI {
    @Override
    public MessageResponse toApiResponse(Message message) {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setId(message.getId());
        messageResponse.setContent(message.getText());
        messageResponse.setBotId(message.getBot().getName());
        messageResponse.setTimestamp(message.getTimestamp());
        return messageResponse;
    }
}
