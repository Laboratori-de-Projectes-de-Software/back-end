package com.alia.back_end_service.domain.game.ports;

import com.alia.back_end_service.domain.game.Game;

import java.util.Optional;

public interface GamePortDB {

    //Gets and Find
    Optional<Game> findById(Integer game_id);

    //Create
    Game saveGame(Game game);

    //Delete
    void deleteGame(Integer game_id);
}
