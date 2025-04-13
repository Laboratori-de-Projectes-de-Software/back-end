package com.debateia.adapter.out.persistence;

import com.debateia.adapter.out.persistence.entities.MessageEntity;
import com.debateia.application.mapper.MessageMapper;
import com.debateia.application.ports.out.persistence.MessageRepository;
import com.debateia.domain.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaMessageRepository implements MessageRepository {
    private final MessageJpaRepository messageJpaRepository;

    @Override
    public List<Messages> findMessagesByMatch(Integer matchId) {
        return messageJpaRepository.findByMatchId(matchId).stream().map(MessageMapper::toDomain).toList();
    }
}
