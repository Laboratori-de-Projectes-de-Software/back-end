package com.debateia.adapter.out.persistence;

import com.debateia.application.mapper.LeagueMapper;
import com.debateia.adapter.out.persistence.entities.LeagueEntity;
import com.debateia.application.ports.out.persistence.LeagueRepository;
import com.debateia.domain.League;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author kjorda
 */

@Component
@RequiredArgsConstructor
public class JpaLeagueRepository implements LeagueRepository {
    
    private final LeagueJpaRepository leagueRepo;
    private final LeagueMapper leagueMapper;
    
    @Override
    public League findByLeagueId(int leagueId) {
        LeagueEntity le = leagueRepo.findById(leagueId).orElse(null);
        return leagueMapper.toDomain(le);
    }

}
