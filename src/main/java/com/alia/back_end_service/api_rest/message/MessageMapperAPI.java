package com.alia.back_end_service.api_rest.message;

import com.alia.back_end_service.api_model.MessageResponseDTO;
import com.alia.back_end_service.domain.message.Message;

public interface MessageMapperAPI {
    MessageResponseDTO toApiResponse(Message message);
}
