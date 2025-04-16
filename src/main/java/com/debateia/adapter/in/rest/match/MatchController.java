package com.debateia.adapter.in.rest.match;


import com.debateia.adapter.mapper.MessageMapper;
import com.debateia.application.ports.in.rest.MessageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController {
    private final MessageUseCase messageUseCase;
    private final MessageMapper messageMapper;

    @GetMapping("/{matchId}/message")
    public ResponseEntity<List<MessageResponseDTO>> getMatchMessages(@PathVariable Integer matchId) {
        // no deberia ir aqui lo de devolver los mensajes? Porque esto es un MatchController? Estoy de acuerdo :D!!!!!
        return ResponseEntity.ok(messageUseCase.getMatchMessages(matchId)
                .stream().map(messageMapper::toResponseDTO).toList());
    }
}
