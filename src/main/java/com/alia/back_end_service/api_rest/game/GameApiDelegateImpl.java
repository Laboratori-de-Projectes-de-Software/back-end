package com.alia.back_end_service.api_rest.game;

import com.alia.back_end_service.api.MatchApiDelegate;
import com.alia.back_end_service.api_model.MessageDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GameApiDelegateImpl implements MatchApiDelegate {
    @Override
    public ResponseEntity<List<MessageDTO>> matchMatchIdMessageGet(Integer matchId) {
        return MatchApiDelegate.super.matchMatchIdMessageGet(matchId);
    }
}
