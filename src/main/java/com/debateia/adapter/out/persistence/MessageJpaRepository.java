package com.debateia.adapter.out.persistence;

import com.debateia.adapter.out.persistence.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageJpaRepository extends JpaRepository<MessageEntity, Integer> {
    List<MessageEntity> findByMatchId(Integer matchId);
}
