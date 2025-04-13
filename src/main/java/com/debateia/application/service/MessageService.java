package com.debateia.application.service;

import com.debateia.application.ports.out.persistence.MessageRepository;
import com.debateia.domain.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public List<Messages> getMatchMessages(Integer matchId) {
        return messageRepository.findMessagesByMatch(matchId);
    }

}
