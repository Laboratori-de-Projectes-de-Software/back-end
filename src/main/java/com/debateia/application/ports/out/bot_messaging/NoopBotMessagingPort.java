package com.debateia.application.ports.out.bot_messaging;

import com.debateia.domain.Bot;
import com.debateia.domain.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@Slf4j
public class NoopBotMessagingPort implements BotMessagingPort {

    @Override
    public void sendMessageToBot(Messages messages, Bot bot) {
        log.info("Sending message: {}\nTo bot: {}\n", messages.getContents(), bot.getName());
    }

}
