package jaumesitos.backend.demo.infrastructure.res.api;


import io.swagger.v3.oas.annotations.Operation;
import jaumesitos.backend.demo.application.service.MatchService;
import jaumesitos.backend.demo.infrastructure.res.dto.MatchDTO;
import jaumesitos.backend.demo.infrastructure.res.mapper.MatchDTOMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@RestController

@RequestMapping("")
@Tag(name = "Matches Controller", description = "Endpoints for managing matches between bots")

public class MatchController {

    private final MatchService matchService;
    @Qualifier("matchDTOMapper")
    private final MatchDTOMapper mapper;

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

    @Operation(summary = "Get all matches from a league", description = "Devuelve todos los enfrentamientos de una liga")
    @GetMapping("/league/{leagueId}/match")
    public ResponseEntity<?> getMatchesByLeagueId(@PathVariable int leagueId) {
        try {
            List<MatchDTO> matchDTOS = matchService.getMatchesByLeagueId(leagueId)
                    .stream()
                    .map(mapper::toDTO)
                    .toList();
            return ResponseEntity.ok(matchDTOS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los partidos");
        }
    }

}
