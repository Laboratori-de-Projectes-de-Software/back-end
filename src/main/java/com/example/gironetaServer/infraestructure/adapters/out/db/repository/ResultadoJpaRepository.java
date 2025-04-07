package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.ResultadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultadoJpaRepository extends JpaRepository<ResultadoEntity, Long> {
    List<ResultadoEntity> findByEnfrentamientoId(Long enfrentamientoId);
}
