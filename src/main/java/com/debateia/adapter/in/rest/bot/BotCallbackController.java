package com.debateia.adapter.in.rest.bot;

import com.debateia.adapter.mapper.MessageMapper;
import com.debateia.application.ports.out.persistence.MessageRepository;
import com.debateia.domain.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BotCallbackController {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @PostMapping("/back/generated-message")
    public ResponseEntity<Void> handleGeneratedMessage(@RequestBody GeneratedMessageDTO dto) {
        Messages message = messageMapper.toDomain(dto);
        messageRepository.save(message);
        return ResponseEntity.ok().build();
    }
}