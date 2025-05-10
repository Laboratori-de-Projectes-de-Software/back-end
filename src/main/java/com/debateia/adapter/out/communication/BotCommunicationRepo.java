package com.debateia.adapter.out.communication;

import com.debateia.adapter.out.bot.BotRepo;
import com.debateia.application.ports.out.persistence.BotCommunicationRepository;
import com.debateia.domain.Bot;
import com.debateia.domain.BotMessageRequest;
import com.debateia.domain.BotMessageResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class BotCommunicationRepo implements BotCommunicationRepository {

    private final BotRepo botRepo;
    private final RestTemplate restTemplate;

    @Override
    public BotMessageResponse sendMessageToBot(Integer botId, BotMessageRequest request) {
        Bot bot = botRepo.findById(botId).orElseThrow(() -> new EntityNotFoundException("Bot no encontrado"));

        String reply = restTemplate.postForObject(bot.getEndpoint(), request, String.class);
        return new BotMessageResponse(reply, botId, LocalDateTime.now());
    }
}
