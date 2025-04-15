package com.debateia.adapter.out.persistence;

import com.debateia.application.mapper.LeagueMapper;
import com.debateia.adapter.out.persistence.entities.LeagueEntity;
import com.debateia.application.ports.out.persistence.LeagueRepository;
import com.debateia.domain.League;
import java.util.List;
import java.util.Optional;
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
    public Optional<League> findById(Integer leagueId) {
        return leagueRepo.findById(leagueId)
                .map(LeagueMapper::toDomain);
    }
    
    @Override
    public League saveLeague(League lg) {
        LeagueEntity toSave = LeagueMapper.toEntity(lg);
        LeagueEntity saved = leagueRepo.save(toSave);
        
        return LeagueMapper.toDomain(saved);
    }
    
    
    @Override
    public void deleteById(Integer leagueId) {
         leagueRepo.deleteById(leagueId);
    }
    
    @Override
    public League updateLeague(Integer leagueId, League lg) {
        LeagueEntity toSave = LeagueMapper.toEntity(lg);
        
        toSave.setId(leagueId);
        LeagueEntity saved = leagueRepo.save(toSave);
        
        return LeagueMapper.toDomain(saved);
    }
    
    @Override
    public List<League> findAll() {
        List<LeagueEntity> leagues = leagueRepo.findAll();
        return leagues.stream().map(LeagueMapper::toDomain).toList();
    }
    
    @Override
    public List<League> findByUserId(Integer userId) {
        List<LeagueEntity> leagues = leagueRepo.findByUser_Id(userId);
        return leagues.stream().map(LeagueMapper::toDomain).toList();
    }

}
