package com.debateia.adapter.out.bot_messaging;

import com.debateia.adapter.in.rest.bot.BotMessageDTO;
import com.debateia.application.ports.out.bot_messaging.BotMessagingPort;
import com.debateia.domain.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class BotMessagingAdapter implements BotMessagingPort {

    private final RestTemplate restTemplate;

    @Override
    public void sendMessageToBot(Messages messages, String botEndPoint) {
        String url = botEndPoint + "/message/" + messages.getMatchId();
        BotMessageDTO requestDTO = new BotMessageDTO();
        requestDTO.setBotId(messages.getBotId());
        requestDTO.setText(messages.getContents());
        requestDTO.setTimestamp(messages.getTimestamp().toString());

        restTemplate.postForEntity(url, requestDTO, Void.class);
    }
}