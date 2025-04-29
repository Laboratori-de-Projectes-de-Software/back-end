package com.example.gironetaServer.infraestructure.adapters.out.db.repository;


import com.example.gironetaServer.infraestructure.adapters.out.db.entities.BotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BotJpaRepository extends JpaRepository<BotEntity, Long> {
    List<BotEntity> findByUsuarioEmail(String email);
    List<BotEntity> findAll();
}
