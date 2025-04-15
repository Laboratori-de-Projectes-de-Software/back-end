package com.debateia.adapter.out.persistence;

import com.debateia.adapter.mapper.LeagueMapper;
import com.debateia.adapter.out.persistence.entities.LeagueEntity;
import com.debateia.application.ports.out.persistence.LeagueRepository;
import com.debateia.domain.League;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LeagueRepo implements LeagueRepository {
    
    private final LeagueJpaRepository leagueJpa;
    
    @Override
    public Optional<League> findById(Integer leagueId) {
        return leagueJpa.findById(leagueId)
                .map(LeagueMapper::toDomain);
    }
    
    @Override
    public League saveLeague(League lg) {
        LeagueEntity toSave = LeagueMapper.toEntity(lg);
        LeagueEntity saved = leagueJpa.save(toSave);
        
        return LeagueMapper.toDomain(saved);
    }
    
    
    @Override
    public void deleteById(Integer leagueId) {
         leagueJpa.deleteById(leagueId);
    }
    
    @Override
    public League updateLeague(Integer leagueId, League lg) {
        LeagueEntity toSave = LeagueMapper.toEntity(lg);
        
        toSave.setId(leagueId);
        LeagueEntity saved = leagueJpa.save(toSave);
        
        return LeagueMapper.toDomain(saved);
    }
    
    @Override
    public List<League> findAll() {
        List<LeagueEntity> leagues = leagueJpa.findAll();
        return leagues.stream().map(LeagueMapper::toDomain).toList();
    }
    
    @Override
    public List<League> findByUserId(Integer userId) {
        List<LeagueEntity> leagues = leagueJpa.findByUser_Id(userId);
        return leagues.stream().map(LeagueMapper::toDomain).toList();
    }

}
