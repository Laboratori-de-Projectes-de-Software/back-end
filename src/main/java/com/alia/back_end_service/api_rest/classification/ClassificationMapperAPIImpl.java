package com.alia.back_end_service.api_rest.classification;

import com.alia.back_end_service.api_model.ClassificationResponseDTO;
import com.alia.back_end_service.domain.classification.Classification;
import org.springframework.stereotype.Component;

@Component
public class ClassificationMapperAPIImpl implements ClassificationMapperAPI {
    @Override
    public ClassificationResponseDTO toApiResponse(Classification classification) {
        ClassificationResponseDTO classificationResponseDTO = new ClassificationResponseDTO();
        classificationResponseDTO.setBotId(classification.getBotId());
        classificationResponseDTO.setnDraws(classification.getTie_matchs());
        classificationResponseDTO.setnLosses(classification.getLose_matchs());
        classificationResponseDTO.setnWins(classification.getWin_matchs());
        classificationResponseDTO.setPoints(classification.getPoints());
        return classificationResponseDTO;
    }
}
