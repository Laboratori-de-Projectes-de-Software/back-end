package com.alia.back_end_service.api_rest.classification;

import com.alia.back_end_service.api_model.ParticipationDTO;
import com.alia.back_end_service.domain.classification.Classification;

public interface ClassificationMapperAPI {
    ParticipationDTO toApiResponse(Classification classification);
}
