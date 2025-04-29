package com.alia.back_end_service.api_rest.game;

import com.alia.back_end_service.api.MatchApiDelegate;
import com.alia.back_end_service.api_model.MessageResponseDTO;
import com.alia.back_end_service.api_rest.message.MessageMapperAPI;
import com.alia.back_end_service.domain.message.port.MessageGetAllByGamePortAPI;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Log4j2
public class GameApiDelegateImpl implements MatchApiDelegate {
    private final MessageGetAllByGamePortAPI messageGetAllByGamePortAPI;
    private final MessageMapperAPI messageMapperAPI;

    @Override
    public ResponseEntity<List<MessageResponseDTO>> matchMatchIdMessageGet(Integer matchId) {
        return ResponseEntity.status(HttpStatus.OK).body(messageGetAllByGamePortAPI.messageGetAllByGame(matchId).stream().map(messageMapperAPI::toApiResponse).toList());
    }
}
