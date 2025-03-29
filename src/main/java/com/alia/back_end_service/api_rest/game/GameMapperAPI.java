package com.alia.back_end_service.api_rest.game;

import com.alia.back_end_service.api_model.GameResponse;
import com.alia.back_end_service.domain.game.Game;
import com.alia.back_end_service.domain.league.League;

public interface GameMapperAPI {
    GameResponse toApiResponse(Game game);
}
