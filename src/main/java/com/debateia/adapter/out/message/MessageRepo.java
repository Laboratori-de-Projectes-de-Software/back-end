package com.debateia.adapter.out.message;

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
    private final MessageMapper messageMapper;

    @Override
    public List<Messages> findMessagesByMatch(Integer matchId) {
        return messageJpaRepository.findByMatchId(matchId).stream().map(messageMapper::toDomain).toList();
    }

    @Override
    public long countByMatchId(int matchId) {
        return messageJpaRepository.countByMatchId(matchId);
    }

    public Messages save(Messages message) {
        MessageEntity entity = messageMapper.toEntity(message);
        messageJpaRepository.save(entity);
        return messageMapper.toDomain(entity);
    }
}

