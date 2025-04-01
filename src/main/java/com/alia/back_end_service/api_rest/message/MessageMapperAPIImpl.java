package com.alia.back_end_service.api_rest.message;

import com.alia.back_end_service.api_model.MessageDTO;
import com.alia.back_end_service.domain.message.Message;
import com.alia.back_end_service.jpa.message.MessageMapper;
import org.springframework.stereotype.Component;

@Component
public class MessageMapperAPIImpl implements MessageMapperAPI {
    @Override
    public MessageDTO toApiResponse(Message message) {
        MessageDTO messageResponse = new MessageDTO();
        messageResponse.setText(message.getText());
        messageResponse.setBotId(message.getBot().getName());
        messageResponse.setTime(message.getTimestamp());
        return messageResponse;
    }
}
