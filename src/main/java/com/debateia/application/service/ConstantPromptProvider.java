package com.debateia.application.service;

import com.debateia.domain.Bot;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConstantPromptProvider implements PromptProvider {

    private static final String OPENING_PROMPT_TEMPLATE = """
    You are an experienced and wise philosopher.
    You have to convince me that ${quality} is the most important human quality there is.
    Meanwhile, I will try to convince you that there is another human quality that is more important.
    You will give only reasoned and philosophically coherent arguments.
    You will defend your belief only while it is reasonable to defend it.
    """;

    private static final String FOLLOWING_PROMPT_TEMPLATE = """
    Now, I will give you my opening statement:
    
    ${opening_statement}
    """;

    private static final String MESSAGE_TAIL = """
    If I have given you solid arguments to convince you, just say:
    "OK, you're right, ${quality} is not the most important human quality."
    And let's end the debate.
    """;

    @Override
    public String provideOpeningPromptForBot(Bot bot) {
        Map<String, String> params = Map.of("quality", bot.getDescription());
        return StringSubstitutor.replace(
                        OPENING_PROMPT_TEMPLATE,
                        params);
    }

    @Override
    public String provideFollowingStatementForBot(Bot bot, String openingStatement) {
        Map<String, String> qualityParams = Map.of("quality", bot.getDescription());
        return StringSubstitutor.replace(
                        OPENING_PROMPT_TEMPLATE,
                        qualityParams)
                + "\n"
                + StringSubstitutor.replace(
                        FOLLOWING_PROMPT_TEMPLATE,
                        Map.of("opening_statement", openingStatement)
                )
                + "\n"
                + StringSubstitutor.replace(
                        MESSAGE_TAIL,
                        qualityParams);
    }

    @Override
    public String provideRegularMessageForBot(Bot bot, String message) {
        return message
                + "\n"
                + StringSubstitutor.replace(
                        MESSAGE_TAIL,
                        Map.of("quality", bot.getDescription()));
    }
}
