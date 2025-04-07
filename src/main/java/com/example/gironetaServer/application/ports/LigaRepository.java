package com.example.gironetaServer.application.ports;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.LeagueEntity;

import java.util.List;
import java.util.Optional;

public interface LigaRepository {
    Optional<LeagueEntity> findById(Long id);
    Optional<LeagueEntity> findByNombre(String nombre);
    List<LeagueEntity> findAll();
    LeagueEntity save(LeagueEntity liga);
    void deleteById(Long id);
}
