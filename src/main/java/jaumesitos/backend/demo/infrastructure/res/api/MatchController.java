package jaumesitos.backend.demo.infrastructure.res.api;


import io.swagger.v3.oas.annotations.Operation;
import jaumesitos.backend.demo.application.service.RespostaService;
import jaumesitos.backend.demo.domain.Resposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController

@RequestMapping("")
@Tag(name = "Matches Controller", description = "Endpoints for managing matches between bots")

public class MatchController {

    private final RespostaService respostaService;

    //CODIS ERROR:
    //HttpStatus.OK -> 200
    //HttpStatus.CREATED -> 201
    //HttpStatus.BAD_REQUEST -> 400
    //HttpStatus.UNAUTHORIZED -> 401
    //HttpStatus.NOT_FOUND -> 404
    //HttpStatus.NOT_FOUND -> 404
    //HttpStatus.REQUEST_TIMEOUT -> 408
    //HttpStatus.CONFLICT -> 409
    //HttpStatus.PAYLOAD_TOO_LARGE -> 413
    //HttpStatus.INTERNAL_SERVER_ERROR - 500
    //HttpStatus.GATEWAY_TIMEOUT -> 504

    //SWAGGER:
    //http://localhost:8080/swagger-ui/index.html#/

    @GetMapping("/match/{matchId}/messages")
    @Operation(summary = "Obtiene todos los mensajes de un enfrentamiento", description = "Devuelve todas las respuestas asociadas al ID del enfrentamiento")
    public ResponseEntity<?> getMensajesByMatchId(@PathVariable int matchId) {
        try {
            List<Resposta> mesages = respostaService.getMesgesByMatchID(matchId);
            return ResponseEntity.ok(mesages);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los mensajes");
        }
    }


}
