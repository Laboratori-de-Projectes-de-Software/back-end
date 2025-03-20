package com.example.gironetaServer.application.ports;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.EnfrentamientoEntity;

import java.util.List;
import java.util.Optional;

public interface EnfrentamientoRepository {
    Optional<EnfrentamientoEntity> findById(Long id);
    List<EnfrentamientoEntity> findByJornadaId(Long jornadaId);
    EnfrentamientoEntity save(EnfrentamientoEntity enfrentamiento);
    void deleteById(Long id);
}
