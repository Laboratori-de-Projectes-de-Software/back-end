package com.debateia.adapter.out.persistence;

import com.debateia.adapter.out.persistence.entities.LeagueBotsId;
import com.debateia.adapter.out.persistence.entities.ParticipationEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParticipationJpaRepository extends JpaRepository<ParticipationEntity, LeagueBotsId>{
    List<ParticipationEntity> findByLeagueId(Integer leagueId);
    List<ParticipationEntity> findByBotId(Integer botId);
    
    
}
