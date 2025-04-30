package org.example.backend.databaseapi.application.port.out.bot;

import org.example.backend.databaseapi.domain.bot.Bot;

public interface UpdateBotPort {

    Bot updateBot(Bot changedBot,Integer id);

}
