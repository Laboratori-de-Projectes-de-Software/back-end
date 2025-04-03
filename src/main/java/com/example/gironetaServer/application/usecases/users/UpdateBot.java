package com.example.gironetaServer.application.usecases.users;

import com.example.gironetaServer.domain.Bot;

public interface UpdateBot {
    void updateBot(Long botId, Bot bot);
}
