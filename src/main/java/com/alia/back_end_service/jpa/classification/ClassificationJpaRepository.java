package com.alia.back_end_service.jpa.classification;

import com.alia.back_end_service.domain.classification.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassificationJpaRepository extends JpaRepository<ClassificationEntity, Integer> {
    List<ClassificationEntity> findAllByLeague_Id(Integer leagueId);
}
