package com.debateia.adapter.out.league;

import com.debateia.adapter.out.participation.ParticipationEntity;
import com.debateia.adapter.out.league.LeagueJpaRepository;
import com.debateia.adapter.mapper.LeagueMapper;
import com.debateia.application.ports.out.persistence.LeagueRepository;
import com.debateia.domain.League;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LeagueRepo implements LeagueRepository {
    
    private final LeagueJpaRepository leagueJpa;
    private final LeagueMapper leagueMapper;
    
    @Override
    public Optional<League> findById(Integer leagueId) {
        return leagueJpa.findById(leagueId)
                .map(leagueMapper::toDomain);
    }
    
    @Override
    public League saveLeague(League lg) {
        LeagueEntity toSave = leagueMapper.toEntity(lg);
        
        List<ParticipationEntity> parts = toSave.getParticipations();
        toSave.setParticipations(new ArrayList<>());
        
        LeagueEntity saved = leagueJpa.save(toSave); // Get league id
        for (ParticipationEntity part : parts) {
            part.setLeagueId(saved.getId()); // Set league id in participations
        }
        
        saved.setParticipations(parts);
        saved = leagueJpa.save(saved); // Update league (save with same id). Will create participations in DB.
        
        return leagueMapper.toDomain(saved);
    }
    
    
    @Override
    public void deleteById(Integer leagueId) {
         leagueJpa.deleteById(leagueId);
    }
    
    @Override
    public League updateLeague(Integer leagueId, League lg) {
        LeagueEntity toSave = leagueMapper.toEntity(lg);
        
        toSave.setId(leagueId);
        LeagueEntity saved = leagueJpa.save(toSave);
        
        return leagueMapper.toDomain(saved);
    }
    
    @Override
    public List<League> findAll() {
        List<LeagueEntity> leagues = leagueJpa.findAll();
        return leagues.stream().map(leagueMapper::toDomain).toList();
    }
    
    @Override
    public List<League> findByUserId(Integer userId) {
        List<LeagueEntity> leagues = leagueJpa.findByUser_Id(userId);
        return leagues.stream().map(leagueMapper::toDomain).toList();
    }

}
