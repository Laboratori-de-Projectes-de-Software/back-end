package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.application.ports.EnfrentamientoRepository;
import com.example.gironetaServer.application.ports.JornadaRepository;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.MatchResponseDTO;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.EnfrentamientoMapper;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.EnfrentamientoEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.JornadaEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EnfrentamientoDatabaseRepository implements EnfrentamientoRepository {

    private final EnfrentamientoJpaRepository enfrentamientoJpaRepository;
    private final JornadaRepository jornadaRepository; //Used here, because database seems to be wrong
    private final EnfrentamientoMapper enfrentamientoMapper;

    public EnfrentamientoDatabaseRepository(EnfrentamientoJpaRepository enfrentamientoJpaRepository, EnfrentamientoMapper enfrentamientoMapper,
                                           JornadaRepository jornadaRepository) {
        this.enfrentamientoJpaRepository = enfrentamientoJpaRepository;
        this.enfrentamientoMapper = enfrentamientoMapper;
        this.jornadaRepository = jornadaRepository;
    }

    @Override
    public Optional<EnfrentamientoEntity> findById(Long id) {
        return enfrentamientoJpaRepository.findById(id);
    }

    @Override
    public List<EnfrentamientoEntity> findByJornadaId(Long jornadaId) {
        return enfrentamientoJpaRepository.findByJornadaId(jornadaId);
    }

    @Override
    public EnfrentamientoEntity save(EnfrentamientoEntity enfrentamiento) {
        return enfrentamientoJpaRepository.save(enfrentamiento);
    }


    @Override
    public void deleteById(Long id) {
        enfrentamientoJpaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<MatchResponseDTO> findByLeagueId(Long leagueId) {
        // Obtener las jornadas de la liga
        List<JornadaEntity> jornadas = jornadaRepository.findByLigaId(leagueId);

        // Recoger todos los enfrentamientos de las jornadas y convertirlos a MatchResponseDTO
        return jornadas.stream()
                .flatMap(jornada ->
                        enfrentamientoJpaRepository.findByJornadaId(jornada.getId()).stream()
                                .map(enfrentamientoEntity -> enfrentamientoMapper.toMatchResponseDTO(enfrentamientoEntity))
                )
                .collect(Collectors.toList());
    }
}
