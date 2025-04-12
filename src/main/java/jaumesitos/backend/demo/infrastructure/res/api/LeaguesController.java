package jaumesitos.backend.demo.infrastructure.res.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jaumesitos.backend.demo.application.service.ClassificacioService;
import jaumesitos.backend.demo.application.service.LligaService;
import jaumesitos.backend.demo.config.DuplicateEntityException;
import jaumesitos.backend.demo.config.ValueNotFoundException;
import jaumesitos.backend.demo.domain.League;
import jaumesitos.backend.demo.domain.Participation;
import jaumesitos.backend.demo.domain.User;
import jaumesitos.backend.demo.infrastructure.db.mapper.LLigaDBOMapper;
import jaumesitos.backend.demo.infrastructure.res.dto.*;
import jaumesitos.backend.demo.infrastructure.res.mapper.BotDTOMapper;
import jaumesitos.backend.demo.infrastructure.res.mapper.LligaDTOMapper;
import jaumesitos.backend.demo.infrastructure.res.mapper.ParticipationResponseDTOMapper;
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
@Tag(name = "Leagues Controller", description = "Endpoints for managing leagues")
public class LeaguesController {
    private final UserDTOMapper userMapper; //convertidor de DTO a classe de lògica de negoci
    private final LligaDTOMapper mapper;
    private final ParticipationResponseDTOMapper participationMapper;
    private final AuthService service; //adaptador
    private final LligaService leagueservice;
    private final ClassificacioService classificationservice;
    @Operation(summary = "Post one league", description = "Post one league given its DTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "League posted successfully", content = @Content),
            @ApiResponse(responseCode = "409", description = "The league already exists", content = @Content)
    })
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

    @Operation(summary = "Get all the leagues", description = "Get all the leagues given a user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Leagues found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LeagueResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Leagues not found", content = @Content)
    })
    @GetMapping("/league/{owner}")
    public ResponseEntity<?> getLeagues(@PathVariable Integer owner) {
        try{
            List<League> ll = leagueservice.getLeagues(owner);
            List<LeagueResponseDTO> response = mapper.toResponseDTOList(ll);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Register a bot to a League", description = "Register a bot to a league given their id's")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Bot registered sucessfully", content = @Content),
            @ApiResponse(responseCode = "409", description = "Bot already registered", content = @Content)
    })
    @PostMapping("/league/{leagueId}/bot")
    public ResponseEntity<?> registerBot(@PathVariable Integer leagueId,@RequestBody IntIdentifierDTO botId) {
        try{
            Participation c = new Participation(leagueId,botId.getIdentifier(),0,0,0,0,0, LocalDateTime.now());
            classificationservice.postClassificacio(c);
        }catch(DuplicateEntityException e){
            return new ResponseEntity<>("La classificació per aquest bot ja existeix", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Classificació del bot creada correctament", HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Get participations", description = "Get the participations of a league")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Participations found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParticipationResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "No participations found", content = @Content)
    })
    @GetMapping("/league/{leagueId}/leaderboard")
    public ResponseEntity<?> getClassification(@PathVariable Integer leagueId){
        try{
            List<ParticipationResponseDTO> classificacio = participationMapper.toResponseDTO(classificationservice.getClassifications(leagueId));
            return new ResponseEntity<>(classificacio,HttpStatus.ACCEPTED);
        }catch(Exception error){
            return new ResponseEntity<>(error.toString(), HttpStatus.NOT_FOUND);
        }

    }


}
