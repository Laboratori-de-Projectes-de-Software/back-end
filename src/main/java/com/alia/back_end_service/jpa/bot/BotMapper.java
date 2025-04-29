package com.alia.back_end_service.jpa.bot;

import com.alia.back_end_service.domain.bot.Bot;

public interface BotMapper {
    Bot toDomain(BotEntity entity);
    BotEntity toEntity(Bot domain);
}
