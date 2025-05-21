package com.debateia.application.service;

import com.debateia.adapter.in.rest.bot.BotMessageDTO;
import com.debateia.adapter.mapper.MessageMapper;
import com.debateia.application.ports.in.rest.BotMessageReceiverUseCase;
import com.debateia.application.ports.out.bot_messaging.BotMessagingPort;
import com.debateia.application.ports.out.persistence.BotRepository;
import com.debateia.application.ports.out.persistence.MessageRepository;
import com.debateia.domain.Bot;
import com.debateia.domain.Messages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BotMessageReceiverService implements BotMessageReceiverUseCase {
    private final MessageMapper messageMapper;
    private final MessageRepository messageRepo;
    private final BotRepository botRepo;
    private final BotMessagingPort messagingPort;

    @Override
    public void receiveAndProcessBotMessage(BotMessageDTO dto) {
        Messages message = messageMapper.toDomain(dto);
        messageRepo.save(message);

        Bot bot = botRepo.findById(dto.getBotId())
                .orElseThrow(() -> new EntityNotFoundException("Bot no encontrado para id " + dto.getBotId()));

        messagingPort.sendMessageToBot(message, bot.getEndpoint());
    }
}