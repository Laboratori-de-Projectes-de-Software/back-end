package com.debateia.adapter.out.persistence;

import com.debateia.adapter.mapper.MessageMapper;
import com.debateia.application.ports.out.persistence.MessageRepository;
import com.debateia.domain.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageRepo implements MessageRepository {
    private final MessageJpaRepository messageJpaRepository;

    @Override
    public List<Messages> findMessagesByMatch(Integer matchId) {
        return messageJpaRepository.findByMatchId(matchId).stream().map(MessageMapper::toDomain).toList();
    }
}
