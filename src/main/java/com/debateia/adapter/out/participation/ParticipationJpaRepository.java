package com.debateia.adapter.out.participation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ParticipationJpaRepository extends JpaRepository<ParticipationEntity, ParticipationId>{
    List<ParticipationEntity> findByLeagueId(Integer leagueId);
    List<ParticipationEntity> findByBotId(Integer botId);
    Optional<ParticipationEntity> findByLeagueIdAndBotId(Integer leagueId, Integer botId);
}
