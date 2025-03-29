package com.alia.back_end_service.api_rest.round;

import com.alia.back_end_service.api_model.RoundResponse;
import com.alia.back_end_service.domain.round.Round;

public interface RoundMapperAPI {
    RoundResponse toApiResponse(Round round);
}
