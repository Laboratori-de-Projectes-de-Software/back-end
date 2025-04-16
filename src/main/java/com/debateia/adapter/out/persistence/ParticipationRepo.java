package com.debateia.adapter.out.persistence;

import com.debateia.adapter.mapper.PartMapper;
import com.debateia.adapter.out.persistence.entities.LeagueBotsId;
import com.debateia.adapter.out.persistence.entities.ParticipationEntity;
import com.debateia.application.ports.out.persistence.ParticipationRepository;
import com.debateia.domain.Participation;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipationRepo implements ParticipationRepository {

    private final ParticipationJpaRepository partJpa;
    
    @Override
    public void createParticipation(Integer leagueId, Integer botId) {
        ParticipationEntity part = ParticipationEntity.builder()
            .leagueId(leagueId)
            .botId(botId)
            .points(0)
            .build();
        
        partJpa.save(part);
    }
    
    @Override
    public Optional<Participation> findById(Integer leagueId, Integer botId) {
        return partJpa.findById(new LeagueBotsId(leagueId, botId)).map(PartMapper::toDomain);
    }
    
    @Override
    public List<Participation> findByLeagueId(Integer leagueId) {
        List<ParticipationEntity> entities = partJpa.findByLeagueId(leagueId);
        return entities.stream().map(PartMapper::toDomain).toList();
    }
}
