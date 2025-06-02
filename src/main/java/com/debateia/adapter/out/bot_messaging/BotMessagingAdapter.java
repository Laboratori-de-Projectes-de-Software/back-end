package com.debateia.adapter.out.bot_messaging;

import com.debateia.adapter.in.rest.bot.BotMessageDTO;
import com.debateia.adapter.mapper.MessageMapper;
import com.debateia.application.ports.out.bot_messaging.BotMessagingPort;
import com.debateia.domain.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class BotMessagingAdapter implements BotMessagingPort {

    private final RestTemplate restTemplate;
    private final MessageMapper messageMapper;

    @Override
    public void sendMessageToBot(Messages messages, String botEndPoint) {
        String url = botEndPoint + "/message/" + messages.getMatchId();
        BotMessageDTO requestDTO = messageMapper.toBotMessage(messages);
        restTemplate.postForEntity(url, requestDTO, Void.class);
    }
}