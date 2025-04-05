package com.alia.back_end_service.api_rest.game;

import com.alia.back_end_service.api_model.MatchDTO;
import com.alia.back_end_service.domain.game.Game;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameMapperAPIImpl implements GameMapperAPI {

    @Override
    public MatchDTO toApiResponse(Game game) {

        int result = resolveResultEnum(game.getResult_local(), game.getResult_visit());

        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setState(game.getState());
        matchDTO.setResult(result);
        List<Integer> fighters = new ArrayList<>();
        fighters.add(game.getBot_local_id());
        fighters.add(game.getBot_visit_id());
        matchDTO.setFighters(fighters);
        matchDTO.setRoundNumber(game.getRoundId());
        return matchDTO;
    }

    // Chapuza temporal
    private int resolveResultEnum(String local, String visit) {
        if (local == null || visit == null) return 0; // En espera o sin resultado

        try {
            int localScore = Integer.parseInt(local);
            int visitScore = Integer.parseInt(visit);
            if (localScore > visitScore) return 1;     // Victoria local
            if (localScore < visitScore) return 2;     // Victoria visitante
            return 3;                                  // Empate
        } catch (NumberFormatException e) {
            return 0; // Resultado no válido
        }
    }

}
