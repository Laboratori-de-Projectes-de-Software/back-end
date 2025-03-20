package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.application.ports.EnfrentamientoRepository;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.EnfrentamientoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EnfrentamientoDatabaseRepository implements EnfrentamientoRepository {

    private final EnfrentamientoJpaRepository enfrentamientoJpaRepository;

    public EnfrentamientoDatabaseRepository(EnfrentamientoJpaRepository enfrentamientoJpaRepository) {
        this.enfrentamientoJpaRepository = enfrentamientoJpaRepository;
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
}
