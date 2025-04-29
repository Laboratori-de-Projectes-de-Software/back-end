package com.alia.back_end_service.jpa.classification;

import com.alia.back_end_service.domain.classification.Classification;

public interface ClassificationMapper {
    Classification toDomain(ClassificationEntity classification);
    ClassificationEntity toEntity(Classification domain);
}
