package com.debateia.application.ports.out.persistence;

import com.debateia.domain.Messages;

import java.util.List;

/**
 *
 * @author kjorda
 */
public interface MessageRepository {
    List<Messages> findMessagesByMatch(Integer matchId);
    long countByMatchId(int matchId);
    void save(Messages message);
}

