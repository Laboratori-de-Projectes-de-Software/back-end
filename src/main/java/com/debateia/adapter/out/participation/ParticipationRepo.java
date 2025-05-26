package com.debateia.adapter.out.participation;

import com.debateia.adapter.mapper.PartMapper;
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
    private final PartMapper partMapper;

    @Override
    public Participation save(Participation participation) {
        return partMapper.toDomain(partJpa.save(partMapper.toEntity(participation)));
    }

    @Override
    public void createParticipation(Integer leagueId, Integer botId) {
        ParticipationEntity part = ParticipationEntity.builder()
            .leagueId(leagueId)
            .botId(botId)
            .points(0)
            .position(0)
            .nWins(0)
            .nDraws(0)
            .nLoses(0)
            .build();
        
        partJpa.save(part);
    }

    @Override
    public Optional<Participation> findById(Integer leagueId, Integer botId) {
        return partJpa.findById(new ParticipationId(leagueId, botId)).map(partMapper::toDomain);
    }
    
    @Override
    public List<Participation> findByLeagueId(Integer leagueId) {
        List<ParticipationEntity> entities = partJpa.findByLeagueId(leagueId);
        return entities.stream().map(partMapper::toDomain).toList();
    }
}
