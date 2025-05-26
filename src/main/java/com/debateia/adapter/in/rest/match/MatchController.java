package com.debateia.adapter.in.rest.match;


import com.debateia.adapter.mapper.MessageMapper;
import com.debateia.adapter.mapper.MatchMapper;
import com.debateia.application.ports.in.rest.MatchUseCase;
import com.debateia.application.ports.in.rest.MessageUseCase;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final MatchUseCase matchUseCase;
    private final MatchMapper matchMapper;

    @GetMapping("/{matchId}/message")
    public ResponseEntity<List<MessageDTO>> getMatchMessages(@PathVariable Integer matchId) {
        // no deberia ir aqui lo de devolver los mensajes? Porque esto es un MatchController? Estoy de acuerdo :D!!!!!

        try {
            return ResponseEntity.ok(messageUseCase.getMatchMessages(matchId)
                    .stream().map(messageMapper::toResponseDTO).toList());
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{matchId}/start")
    public ResponseEntity<MatchResponseDTO> startMatch(@PathVariable Integer matchId) {
        try {
            return ResponseEntity.ok(matchMapper.toResponseDTO(matchUseCase.startMatch(matchId)));

        } catch (EntityNotFoundException e) {
            // Match o bot no encontrados
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (RuntimeException e) {
            // El Match ya fue iniciado anteriormente
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
