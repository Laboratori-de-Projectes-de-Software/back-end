package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.LigaEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigaJpaRepository extends JpaRepository<LigaEntity, Long> {
    List<LigaEntity> findByUsuarioEmail(String email);
}