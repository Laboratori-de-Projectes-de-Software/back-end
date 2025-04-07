package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.application.ports.ResultadoRepository;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.ResultadoEntity;

import java.util.List;

public class ResultadoDatabaseRepository implements ResultadoRepository {
    private final ResultadoJpaRepository resultadoJpaRepository;

    public ResultadoDatabaseRepository(ResultadoJpaRepository resultadoJpaRepository) {
        this.resultadoJpaRepository = resultadoJpaRepository;
    }

    @Override
    public ResultadoEntity findById(Long id) {
        return null;
    }

    @Override
    public List<ResultadoEntity> findByEnfrentamientoId(Long enfrentamientoId) {
        return List.of();
    }

    @Override
    public ResultadoEntity save(ResultadoEntity resultado) {
        return resultadoJpaRepository.save(resultado);
    }

    @Override
    public void deleteById(Long id) {

    }
}
