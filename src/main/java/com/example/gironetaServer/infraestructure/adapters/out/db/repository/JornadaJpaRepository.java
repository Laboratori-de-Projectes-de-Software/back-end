package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.JornadaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JornadaJpaRepository extends JpaRepository<JornadaEntity, Long> {
    List<JornadaEntity> findByLigaId(Long ligaId);
}
