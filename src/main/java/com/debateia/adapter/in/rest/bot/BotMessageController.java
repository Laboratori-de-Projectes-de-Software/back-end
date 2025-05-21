package com.debateia.adapter.in.rest.bot;

import com.debateia.application.ports.in.rest.BotMessageReceiverUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
public class BotMessageController {
    private final BotMessageReceiverUseCase useCase;

    @PostMapping("/bot/message")
    public ResponseEntity<Void> receiveMessage(@RequestBody BotMessageDTO dto) {
        useCase.receiveAndProcessBotMessage(dto);
        return ResponseEntity.ok().build();
    }
}