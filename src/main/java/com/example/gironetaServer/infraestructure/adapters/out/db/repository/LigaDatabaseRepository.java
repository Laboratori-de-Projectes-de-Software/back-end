package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.application.ports.LeagueRepository;
import com.example.gironetaServer.domain.League;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.LeagueEntity;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.LigaMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class LigaDatabaseRepository implements LeagueRepository {

    private final LigaJpaRepository leagueJpaRepository;
    private final LigaMapper leagueMapper;

    public LigaDatabaseRepository(LigaJpaRepository leagueJpaRepository, LigaMapper leagueMapper) {
        this.leagueJpaRepository = leagueJpaRepository;
        this.leagueMapper = leagueMapper;
    }

    @Override
    @Transactional
    public Optional<League> findById(Long id) {
        return leagueJpaRepository.findById(id)
                .map(leagueMapper::toDomain);
    }

    @Override
    @Transactional
    public List<League> findByUserId(Long userId) {
        return leagueJpaRepository.findByUsuarioId(userId)
                .stream()
                .map(ligaEntity -> leagueMapper.toDomain(ligaEntity))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<League> findAll() {
        return leagueJpaRepository.findAll()
                .stream()
                .map(leagueMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public League save(League league) {
        LeagueEntity leagueEntity = leagueMapper.toEntity(league);
        leagueEntity = leagueJpaRepository.save(leagueEntity);
        return leagueMapper.toDomain(leagueEntity);
    }

    @Override
    public void deleteById(Long id) {
        leagueJpaRepository.deleteById(id);
    }
}