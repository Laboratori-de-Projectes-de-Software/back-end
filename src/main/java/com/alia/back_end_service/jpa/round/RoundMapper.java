package com.alia.back_end_service.jpa.round;

import com.alia.back_end_service.domain.round.Round;

public interface RoundMapper {
    Round toDomain(RoundEntity entity);
    RoundEntity toEntity(Round domain);
}
