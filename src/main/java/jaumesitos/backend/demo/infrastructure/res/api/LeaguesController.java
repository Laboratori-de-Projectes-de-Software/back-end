package jaumesitos.backend.demo.infrastructure.res.api;

import io.swagger.v3.oas.annotations.Operation;
import jaumesitos.backend.demo.application.service.ClassificacioService;
import jaumesitos.backend.demo.application.service.LligaService;
import jaumesitos.backend.demo.application.service.MatchService;
import jaumesitos.backend.demo.config.DuplicateEntityException;
import jaumesitos.backend.demo.domain.Classificacio;
import jaumesitos.backend.demo.domain.League;
import jaumesitos.backend.demo.domain.User;
import jaumesitos.backend.demo.infrastructure.db.mapper.LLigaDBOMapper;
import jaumesitos.backend.demo.infrastructure.res.dto.*;
import jaumesitos.backend.demo.infrastructure.res.mapper.BotDTOMapper;
import jaumesitos.backend.demo.infrastructure.res.mapper.LligaDTOMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import jaumesitos.backend.demo.application.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import jaumesitos.backend.demo.infrastructure.res.mapper.UserDTOMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController

@RequestMapping("")
@Tag(name = "Leagues Controller", description = "Endpoints for managing users")
public class LeaguesController {
    @Qualifier("userDTOMapper")
    private final UserDTOMapper userMapper; //convertidor de DTO a classe de lògica de negoci
    @Qualifier("lligaDTOMapper")
    private final LligaDTOMapper mapper;
    private final AuthService service; //adaptador
    private final LligaService leagueservice;
    private final ClassificacioService classificationservice;
    public final MatchService matchservice;

    @PostMapping("/league")
    public ResponseEntity<?> postLeague(@RequestBody LeagueDTO dto) {
        try{
             League l = mapper.toDomain(dto);
            leagueservice.postLliga(l);
        }catch(DuplicateEntityException e){
            return new ResponseEntity<>("La lliga ja existeix", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("LLiga creada correctament", HttpStatus.ACCEPTED);
    }

    @GetMapping("/league/{owner}")
    public ResponseEntity<?> getLeagues(@PathVariable Integer owner) {
        try{
            List<League> ll = leagueservice.getLeagues(owner);
            List<LeagueResponseDTO> response = mapper.toResponseDTOList(ll);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return new ResponseEntity<>("La lliga ja existeix", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/league/{leagueId}/bot")
    public ResponseEntity<?> registerBot(@PathVariable Integer leagueId,@RequestBody IntIdentifierDTO botId) {
        try{
            Classificacio c = new Classificacio(leagueId,botId.getIdentifier(),0,0,0,0,0, LocalDateTime.now());
            classificationservice.postClassificacio(c);
        }catch(DuplicateEntityException e){
            return new ResponseEntity<>("La classificació per aquest bot ja existeix", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Classificació del bot creada correctament", HttpStatus.ACCEPTED);
    }

    @GetMapping("/league/id/{leagueId}")
    public ResponseEntity<?> findLeagueById(@PathVariable Integer leagueId) {
        try {
            League league = leagueservice.getLeagueById(leagueId);
            LeagueResponseDTO response = mapper.toResponseDTO(league);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Liga no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/league/{leagueId}")
    public ResponseEntity<?> deleteLeagueById(@PathVariable int leagueId) {
        try {
            leagueservice.deleteLeagueById(leagueId);
            return ResponseEntity.ok("Liga eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la liga");
        }
    }
}
