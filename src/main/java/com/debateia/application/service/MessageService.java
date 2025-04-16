package com.debateia.application.service;

import com.debateia.application.ports.in.rest.MessageUseCase;
import com.debateia.application.ports.out.persistence.MessageRepository;
import com.debateia.domain.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService implements MessageUseCase {
    private final MessageRepository messageRepository;

    @Override
    public List<Messages> getMatchMessages(Integer matchId) {
        return messageRepository.findMessagesByMatch(matchId);
    }

}
