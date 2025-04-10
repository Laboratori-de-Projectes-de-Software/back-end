package com.alia.back_end_service.api_rest.game;

import com.alia.back_end_service.api_model.MatchResponseDTO;
import com.alia.back_end_service.domain.game.Game;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameMapperAPIImpl implements GameMapperAPI {

    @Override
    public MatchResponseDTO toApiResponse(Game game) {

        int result = resolveResultEnum(game.getResult_local(), game.getResult_visit());

        MatchResponseDTO matchDTO = new MatchResponseDTO();
        matchDTO.setState(game.getState());
        matchDTO.setResult(result);
        List<String> fighters = new ArrayList<>();
        fighters.add(game.getBot_local_name());
        fighters.add(game.getBot_visit_name());
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
