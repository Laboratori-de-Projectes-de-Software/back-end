package com.debateia.application.ports.in.rest;

import com.debateia.domain.Messages;

public interface BotMessageReceiverUseCase {
    void receiveAndProcessBotMessage(int matchId, Messages dto);
}
