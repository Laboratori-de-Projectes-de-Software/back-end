package com.debateia.application.ports.in.rest;

import com.debateia.adapter.in.rest.bot.BotMessageDTO;

public interface BotMessageReceiverUseCase {
    void receiveAndProcessBotMessage(BotMessageDTO dto);
}
