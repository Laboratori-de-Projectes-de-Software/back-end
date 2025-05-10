package com.debateia.application.ports.out.persistence;

import com.debateia.domain.BotMessageRequest;
import com.debateia.domain.BotMessageResponse;

public interface BotCommunicationRepository {
    BotMessageResponse sendMessageToBot(Integer botId, BotMessageRequest request);
}
