package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.application.ports.LeagueRepository;
import com.example.gironetaServer.application.ports.ParticipacionRepository;
import com.example.gironetaServer.domain.League;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.ParticipationResponseDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.LigaMapper;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.ParticipacionMapper;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.LeagueEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.ParticipacionEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ParticipacionDatabaseRepository implements ParticipacionRepository {

    private final ParticipacionJpaRepository participacionJpaRepository;
    private final ParticipacionMapper participacionMapper;

    public ParticipacionDatabaseRepository(ParticipacionJpaRepository participacionJpaRepository, ParticipacionMapper participacionMapper) {
        this.participacionJpaRepository = participacionJpaRepository;
        this.participacionMapper = participacionMapper;
    }

    @Override
    @Transactional
    public List<ParticipationResponseDto> findByLeagueId(Long leagueId) {

        return participacionJpaRepository.findByLeagueId(leagueId)
                .stream()
                .map( participationEntity-> participacionMapper.toParticipationResponseDto(participationEntity))
                .collect(Collectors.toList());

    }
}