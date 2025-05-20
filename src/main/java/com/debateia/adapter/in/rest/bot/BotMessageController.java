package com.debateia.adapter.in.rest.bot;

import com.debateia.adapter.mapper.MessageMapper;
import com.debateia.adapter.out.bot_messaging.BotMessagingAdapter;
import com.debateia.application.ports.in.rest.BotMessageReceiverUserCase;
import com.debateia.application.ports.out.persistence.BotRepository;
import com.debateia.domain.Bot;
import com.debateia.domain.Messages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
public class BotMessageController {

    private final MessageMapper messageMapper;
    private final BotMessageReceiverUserCase botMessageReceiver;
    private final BotRepository botRepository;
    private final BotMessagingAdapter botMessaging;

    @PostMapping("/message")
    public ResponseEntity<Void> receiveMessageFromBot(@RequestBody BotMessageDTO dto) {
        Messages message = messageMapper.toDomain(dto);
        botMessageReceiver.receiveBotMessage(message);

        Bot bot = botRepository.findById(message.getBotId())
                .orElseThrow(() -> new EntityNotFoundException("Bot no encontrado para id " + dto.getBotId()));

        botMessaging.sendMessageToBot(message, dto.getSystem_message(), bot.getEndpoint());
        return ResponseEntity.ok().build();
    }
}