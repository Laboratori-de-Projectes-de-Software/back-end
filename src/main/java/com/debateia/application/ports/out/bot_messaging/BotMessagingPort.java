package com.debateia.application.ports.out.bot_messaging;

import com.debateia.domain.Messages;

public interface BotMessagingPort {
    void sendMessageToBot(Messages messages, String botEndPoint);
}
