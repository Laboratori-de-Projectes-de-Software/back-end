package com.alia.back_end_service.api_rest.classification;

import com.alia.back_end_service.api_model.ClassificationResponse;
import com.alia.back_end_service.domain.classification.Classification;
import org.springframework.stereotype.Component;

@Component
public class ClassificationMapperAPIImpl implements ClassificationMapperAPI {
    @Override
    public ClassificationResponse toApiResponse(Classification classification) {
        ClassificationResponse classificationResponse = new ClassificationResponse();
        classificationResponse.setId(classification.getId());
        classificationResponse.setPoints(classification.getPoints());
        classificationResponse.setNumberMatchs(classification.getNumber_matchs());
        classificationResponse.setWinMatchs(classification.getWin_matchs());
        classificationResponse.setTieMatchs(classification.getTie_matchs());
        classificationResponse.setLoseMatchs(classification.getLose_matchs());
        return classificationResponse;
    }
}
