package com.example.demo.adapters.out.persistence.League;

import com.example.demo.application.port.out.LeagueRepository;
import com.example.demo.domain.model.League;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Adaptador que implementa el puerto de salida usando Spring Data JPA.
 */
@Component
public class LeaguePersistenceAdapter implements LeagueRepository {

    private final SpringDataLeagueRepository jpaRepository;

    public LeaguePersistenceAdapter(SpringDataLeagueRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public League save(League league) {
        // Mapeo de dominio a entidad JPA
        LeagueEntity entity = new LeagueEntity();
        entity.setId(league.getId());
        entity.setName(league.getName());
        entity.setUrlImagen(league.getUrlImagen());
        entity.setRounds(league.getRounds());
        entity.setMatchTime(league.getMatchTime());
        entity.setBots(league.getBots());
        entity.setCreatedAt(league.getCreatedAt());
        
        LeagueEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public League findById(Long leagueId) {
        LeagueEntity entity = jpaRepository.findById(leagueId)
                .orElseThrow(() -> new RuntimeException("League not found with id: " + leagueId));
        return toDomain(entity);
    }

    @Override
    public List<League> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private League toDomain(LeagueEntity entity) {
        League league = new League(entity.getId(), entity.getName(), entity.getUrlImagen(), entity.getRounds(), entity.getMatchTime(), entity.getBots());
        return league;
    }
}
