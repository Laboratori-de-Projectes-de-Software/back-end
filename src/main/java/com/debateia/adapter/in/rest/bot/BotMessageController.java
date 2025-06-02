package com.debateia.adapter.in.rest.bot;

import com.debateia.adapter.mapper.MessageMapper;
import com.debateia.application.ports.in.rest.BotMessageReceiverUseCase;
import com.debateia.domain.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class BotMessageController {
    private final BotMessageReceiverUseCase useCase;
    private final MessageMapper messageMapper;

    @PostMapping("/{matchId}")
    public ResponseEntity<Void> receiveMessage(@PathVariable Integer matchId, @RequestBody BotMessageDTO dto) {
        Messages message = messageMapper.toDomain(dto);
        message.setMatchId(matchId);
        useCase.receiveAndProcessBotMessage(matchId, message);
        return ResponseEntity.ok().build();
    }
}