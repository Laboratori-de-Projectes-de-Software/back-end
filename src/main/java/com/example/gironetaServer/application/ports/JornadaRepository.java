package com.example.gironetaServer.application.ports;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.JornadaEntity;

import java.util.List;
import java.util.Optional;

public interface JornadaRepository {
    Optional<JornadaEntity> findById(Long id);
    List<JornadaEntity> findByLigaId(Long ligaId);
    JornadaEntity save(JornadaEntity jornada);
    void deleteById(Long id);
}
