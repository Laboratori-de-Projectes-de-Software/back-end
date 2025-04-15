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
    
    @Override
    public League findById(int leagueId) {
        LeagueEntity le = leagueRepo.findById(leagueId).orElse(null);
        return LeagueMapper.toDomain(le);
    }
    
    @Override
    public League saveLeague(League lg) {
        LeagueEntity toSave = LeagueMapper.toEntity(lg);
        LeagueEntity saved = leagueRepo.save(toSave);
        return LeagueMapper.toDomain(saved);
    }
    
    
    @Override
    public void deleteById(int leagueId) {
         leagueRepo.deleteById(leagueId);
    }
    

}
