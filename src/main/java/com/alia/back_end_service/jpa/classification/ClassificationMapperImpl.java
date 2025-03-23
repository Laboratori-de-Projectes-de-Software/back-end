package com.alia.back_end_service.jpa.classification;

import com.alia.back_end_service.domain.classification.Classification;
import org.springframework.stereotype.Component;

@Component
public class ClassificationMapperImpl implements ClassificationMapper {


    @Override
    public Classification toDomain(ClassificationEntity classification) {
        return null;
    }

    @Override
    public ClassificationEntity toEntity(Classification domain) {
        return null;
    }
}
