package com.debateia.application.ports.in.rest;

import com.debateia.domain.Messages;
import java.util.List;

/**
 *
 * @author kjorda
 */
public interface MessageUseCase {
    List<Messages> getMatchMessages(Integer matchId);
}
