package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.LigaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LigaJpaRepository extends JpaRepository<LigaEntity, Long> {
    Optional<LigaEntity> findByNombre(String nombre);
}
