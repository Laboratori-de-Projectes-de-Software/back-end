package com.debateia.application.service;

import com.debateia.application.ports.in.rest.BotMessageReceiverUseCase;
import com.debateia.application.ports.in.rest.BotUseCase;
import com.debateia.application.ports.in.rest.MatchUseCase;
import com.debateia.application.ports.out.bot_messaging.BotMessagingPort;
import com.debateia.application.ports.out.persistence.MessageRepository;
import com.debateia.domain.Bot;
import com.debateia.domain.Match;
import com.debateia.domain.Messages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BotMessageReceiverService implements BotMessageReceiverUseCase {

    private final MatchUseCase matchUseCase;
    private final BotUseCase botUseCase;
    private final MessageRepository messageRepo;
    private final BotMessagingPort messagingPort;
    private final PromptProvider promptProvider;

    public void receiveAndProcessBotMessage(int matchId, Messages message) {
        Match match = matchUseCase.getMatchById(matchId);
        Integer botId = message.getBotId();

        int opponentBotId;
        if (!Objects.equals(botId, match.getBot1id())) {
            opponentBotId = match.getBot1id();
        } else if (!Objects.equals(botId, match.getBot2id())) {
            opponentBotId = match.getBot2id();
        } else {
            throw new EntityNotFoundException("Oponente no encontrado para el bot con id " + botId);
        }

        Bot opponentBot = botUseCase.getBotById(opponentBotId);

        messagingPort.sendMessageToBot(buildMessageForOpponent(message, opponentBot, match.getMessageIds().size() == 1), opponentBot);
    }

    private Messages buildMessageForOpponent(Messages message, Bot opponentBot, boolean isFirstMessage) {
        Messages newMessage = new Messages();
        newMessage.setBotId(opponentBot.getId());
        newMessage.setMatchId(message.getMatchId());
        newMessage.setTimestamp(LocalDateTime.now());
        if(isFirstMessage) {
            newMessage.setContents(promptProvider.provideFollowingStatementForBot(opponentBot, message.getContents()));
        } else {
            newMessage.setContents(promptProvider.provideRegularMessageForBot(opponentBot, message.getContents()));
        }
        return newMessage;
    }

}