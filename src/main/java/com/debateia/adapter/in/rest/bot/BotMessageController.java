package com.debateia.adapter.in.rest.bot;

import com.debateia.adapter.out.bot_messaging.BotMessagingAdapter;
import com.debateia.application.ports.in.rest.BotMessageReceiverUserCase;
import com.debateia.application.ports.out.persistence.BotRepository;
import com.debateia.domain.Bot;
import com.debateia.domain.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class BotMessageController {

    private final BotMessageReceiverUserCase botMessageReceiver;
    private final BotRepository botRepository;
    private final BotMessagingAdapter botMessaging;

    @PostMapping("/{matchId}")
    public ResponseEntity<Void> receiveMessageFromBot(
            @PathVariable Integer matchId,
            @RequestBody BotMessageDTO dto) {
        Messages message = new Messages();
        message.setBotId(dto.getBotId());
        message.setContents(dto.getText());
        message.setTimestamp(LocalDateTime.parse(dto.getTimestamp()));
        message.setMatchId(matchId);

        botMessageReceiver.receiveBotMessage(message);

        Bot bot = botRepository.findById(message.getBotId()).orElseThrow();
        String botApiUrl = bot.getEndpoint();
        botMessaging.sendMessageToBot(message, botApiUrl);

        return ResponseEntity.ok().build();
    }
}