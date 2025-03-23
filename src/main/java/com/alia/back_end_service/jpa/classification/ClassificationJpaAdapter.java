package com.alia.back_end_service.jpa.classification;

import com.alia.back_end_service.domain.classification.ports.ClassificationPortDB;
import org.springframework.stereotype.Component;

@Component
public class ClassificationJpaAdapter implements ClassificationPortDB {

    private final ClassificationJpaRepository classificationJpaRepository;

    private final ClassificationMapper classificationMapper;

    public ClassificationJpaAdapter(ClassificationJpaRepository classificationJpaRepository, ClassificationMapper classificationMapper) {
        this.classificationJpaRepository = classificationJpaRepository;
        this.classificationMapper = classificationMapper;
    }
}
