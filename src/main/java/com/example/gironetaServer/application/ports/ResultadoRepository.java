package com.example.gironetaServer.application.ports;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.ResultadoEntity;

import java.util.List;

public interface ResultadoRepository {
    ResultadoEntity findById(Long id);
    List<ResultadoEntity> findByEnfrentamientoId(Long enfrentamientoId);
    ResultadoEntity save(ResultadoEntity resultado);
    void deleteById(Long id);
}
