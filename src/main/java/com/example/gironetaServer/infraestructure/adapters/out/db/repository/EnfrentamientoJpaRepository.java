package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.EnfrentamientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnfrentamientoJpaRepository extends JpaRepository<EnfrentamientoEntity, Long> {
    List<EnfrentamientoEntity> findByJornadaId(Long jornadaId);
}
