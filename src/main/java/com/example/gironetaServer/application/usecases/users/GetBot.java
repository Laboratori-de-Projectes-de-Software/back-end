package com.example.gironetaServer.application.usecases.users;

import com.example.gironetaServer.domain.Bot;

import java.util.List;
import java.util.Optional;

public interface GetBot {
    Optional<Bot> getBotById(Long id);
    List<Bot> getAllBots();

    List<Bot> getBotsByOwner(Long id);
}
