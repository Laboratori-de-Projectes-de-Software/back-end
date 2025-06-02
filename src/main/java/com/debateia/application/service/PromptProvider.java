package com.debateia.application.service;

import com.debateia.domain.Bot;

public interface PromptProvider {

    String provideOpeningPromptForBot(Bot bot);

    String provideFollowingStatementForBot(Bot bot, String openingStatement);

    String provideRegularMessageForBot(Bot bot, String message);

}
