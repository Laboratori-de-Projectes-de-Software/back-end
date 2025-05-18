package com.debateia.application.service;

import com.debateia.application.ports.in.rest.BotMessageReceiverUserCase;
import com.debateia.application.ports.out.persistence.MessageRepository;
import com.debateia.domain.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BotMessageReceiverService implements BotMessageReceiverUserCase {

    private final MessageRepository messageRepository;

    @Override
    public void receiveBotMessage(Messages response) {
        messageRepository.save(response);
    }
}