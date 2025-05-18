package com.debateia.adapter.out.message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageJpaRepository extends JpaRepository<MessageEntity, Integer> {
    List<MessageEntity> findByMatchId(Integer matchId);
    long countByMatch_Id(int matchId);
}
