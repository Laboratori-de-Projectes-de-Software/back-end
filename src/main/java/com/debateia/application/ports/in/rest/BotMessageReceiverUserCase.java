package com.debateia.application.ports.in.rest;

import com.debateia.domain.Messages;

public interface BotMessageReceiverUserCase {
    void receiveBotMessage(Messages response);
}
