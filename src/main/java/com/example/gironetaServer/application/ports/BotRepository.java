package com.example.gironetaServer.application.ports;

import com.example.gironetaServer.domain.Bot;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.BotEntity;

import java.util.List;
import java.util.Optional;

public interface BotRepository {
    Optional<Bot> findById(Long id);  // Cambiado de BotEntity a Bot
    List<Bot> findByUsuarioCorreo(String correo);  // Cambiado de BotEntity a Bot
    Bot save(Bot bot);
    void deleteById(Long id);
}