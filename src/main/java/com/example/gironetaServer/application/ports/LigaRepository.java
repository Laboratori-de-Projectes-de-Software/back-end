package com.example.gironetaServer.application.ports;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.LigaEntity;

import java.util.List;
import java.util.Optional;

public interface LigaRepository {
    Optional<LigaEntity> findById(Long id);
    Optional<LigaEntity> findByNombre(String nombre);
    List<LigaEntity> findAll();
    LigaEntity save(LigaEntity liga);
    void deleteById(Long id);
}
