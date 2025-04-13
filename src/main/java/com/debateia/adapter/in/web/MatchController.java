package com.debateia.adapter.in.web;


import com.debateia.adapter.in.web.dto.response.MessageResponseDTO;
import com.debateia.application.mapper.MessageMapper;
import com.debateia.application.service.MatchService;
import com.debateia.application.service.MessageService;
import lombok.Getter;
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
    private final MessageService messageService;

    @GetMapping("/{matchId}/message")
    public ResponseEntity<List<MessageResponseDTO>> getMatchMessages(@PathVariable Integer matchId) {
        // no deberia ir aqui lo de devolver los mensajes? Porque esto es un MatchController? Estoy de acuerdo :D!!!!!
        return ResponseEntity.ok(messageService.getMatchMessages(matchId)
                .stream().map(MessageMapper::toResponseDTO).toList());
    }
}
