package com.debateia.application.ports.out.bot_messaging;

import com.debateia.application.ports.out.persistence.MessageRepository;
import com.debateia.domain.Bot;
import com.debateia.domain.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@Slf4j
@RequiredArgsConstructor
public class SaveAndPrintBotMessagingPort implements BotMessagingPort {

    private final MessageRepository messageRepository;

    @Override
    public void sendMessageToBot(Messages messages, Bot bot) {
        messageRepository.save(messages);
        log.info("Sending message: {}\nTo bot: {}\n", messages.getContents(), bot.getName());
    }

}
