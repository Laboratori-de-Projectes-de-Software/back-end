package com.debateia.application.ports.in.bot_messaging;

import com.debateia.domain.Messages;

public interface BotMessageReceiverPort {
    void receiveBotMessage(Messages response);
}
