package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.application.ports.LigaRepository;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.LigaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LigaDatabaseRepository implements LigaRepository {

    private final LigaJpaRepository ligaJpaRepository;

    public LigaDatabaseRepository(LigaJpaRepository ligaJpaRepository) {
        this.ligaJpaRepository = ligaJpaRepository;
    }

    @Override
    public Optional<LigaEntity> findById(Long id) {
        return ligaJpaRepository.findById(id);
    }

    @Override
    public Optional<LigaEntity> findByNombre(String nombre) {
        return ligaJpaRepository.findByNombre(nombre);
    }

    @Override
    public List<LigaEntity> findAll() {
        return ligaJpaRepository.findAll();
    }

    @Override
    public LigaEntity save(LigaEntity liga) {
        return ligaJpaRepository.save(liga);
    }

    @Override
    public void deleteById(Long id) {
        ligaJpaRepository.deleteById(id);
    }
}
