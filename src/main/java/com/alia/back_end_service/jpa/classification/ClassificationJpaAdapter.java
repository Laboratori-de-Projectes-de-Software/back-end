package com.alia.back_end_service.jpa.classification;

import com.alia.back_end_service.domain.classification.Classification;
import com.alia.back_end_service.domain.classification.ports.ClassificationPortDB;
import com.alia.back_end_service.jpa.bot.BotEntity;
import com.alia.back_end_service.jpa.bot.BotJpaRepository;
import com.alia.back_end_service.jpa.league.LeagueEntity;
import com.alia.back_end_service.jpa.league.LeagueJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ClassificationJpaAdapter implements ClassificationPortDB {

    private final ClassificationJpaRepository classificationJpaRepository;
    private final LeagueJpaRepository leagueJpaRepository;
    private final BotJpaRepository botJpaRepository;
    private final ClassificationMapper classificationMapper;

    @Override
    public void save(Classification classification) {
        BotEntity botEntity = botJpaRepository.findById(classification.getBotId()).orElse(null);
        LeagueEntity leagueEntity = leagueJpaRepository.findById(classification.getLeagueId()).orElse(null);
        ClassificationEntity classificationEntity = classificationMapper.toEntity(classification);
        classificationEntity.setBot(botEntity);
        classificationEntity.setLeague(leagueEntity);
        classificationJpaRepository.save(classificationEntity);
    }

    @Override
    public List<Classification> findByLeague(Integer leagueId) {
        List<ClassificationEntity> classificationEntities = classificationJpaRepository.findAllByLeague_Id(leagueId);
        return classificationEntities.stream().map(classificationMapper::toDomain).toList();
    }
}
