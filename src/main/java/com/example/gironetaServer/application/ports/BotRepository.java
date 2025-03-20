package com.example.gironetaServer.application.ports;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.BotEntity;

import java.util.List;
import java.util.Optional;

public interface BotRepository {
    Optional<BotEntity> findById(Long id);
    List<BotEntity> findByUsuarioCorreo(String correo);
    BotEntity save(BotEntity bot);
    void deleteById(Long id);
}