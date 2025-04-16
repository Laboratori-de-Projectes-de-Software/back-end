package com.example.gironetaServer.application.usecases.users;

import com.example.gironetaServer.domain.Bot;

public interface UpdateBot {
    Bot updateBot(Long botId, Bot bot);
}
