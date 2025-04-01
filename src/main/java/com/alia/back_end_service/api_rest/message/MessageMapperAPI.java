package com.alia.back_end_service.api_rest.message;

import com.alia.back_end_service.api_model.MessageDTO;
import com.alia.back_end_service.domain.message.Message;

public interface MessageMapperAPI {
    MessageDTO toApiResponse(Message message);
}
