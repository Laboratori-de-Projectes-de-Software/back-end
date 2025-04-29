package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.LeagueEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigaJpaRepository extends JpaRepository<LeagueEntity, Long> {
    List<LeagueEntity> findByUsuarioEmail(String email);

    List<LeagueEntity> findByUsuarioId(Long userId);
}