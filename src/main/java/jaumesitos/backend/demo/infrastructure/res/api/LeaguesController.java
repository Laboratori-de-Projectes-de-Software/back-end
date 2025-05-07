package jaumesitos.backend.demo.infrastructure.res.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jaumesitos.backend.demo.application.service.AuthService;
import jaumesitos.backend.demo.application.service.ParticipationService;
import jaumesitos.backend.demo.application.service.LligaService;
import jaumesitos.backend.demo.application.service.MatchService;
import jaumesitos.backend.demo.config.DuplicateEntityException;
import jaumesitos.backend.demo.domain.League;
import jaumesitos.backend.demo.domain.Participation;
import jaumesitos.backend.demo.domain.User;
import jaumesitos.backend.demo.infrastructure.res.dto.*;
import jaumesitos.backend.demo.infrastructure.res.mapper.LligaDTOMapper;
import jaumesitos.backend.demo.infrastructure.res.mapper.MatchDTOMapper;
import jaumesitos.backend.demo.infrastructure.res.mapper.ParticipationResponseDTOMapper;
import jaumesitos.backend.demo.infrastructure.security.AuthUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController

@RequestMapping("/api/v0")
@Tag(name = "Leagues Controller", description = "Endpoints for managing leagues")
public class LeaguesController {
    @Qualifier("lligaDTOMapper")
    private final LligaDTOMapper mapper;
    private final LligaService leagueservice;
    private final ParticipationService classificationservice;
    private final ParticipationResponseDTOMapper participationMapper;
    private final MatchService matchService;
    private final AuthService authService;
    @Qualifier("matchDTOMapper")
    private final MatchDTOMapper matchmapper;

    @Operation(summary = "Create a new league", description = "This endpoint allows a user to create a new league")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "League posted successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LeagueResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "The league already exists", content = @Content),
            @ApiResponse(responseCode = "500", description = "Algun dels camps passats es incorrecte", content = @Content)
    })
    @PostMapping("/league")
    public ResponseEntity<?> postLeague(@RequestBody LeagueDTO dto) {
        try{
            String email = AuthUtil.getCurrentUserEmail();
            User user = authService.getUserByEmail(email);

            League l = mapper.toDomain(dto);
            l.setUserId(user.getId());
            LeagueResponseDTO creada =  mapper.toResponseDTO(leagueservice.postLliga(l));
            return ResponseEntity.ok(creada);

        }catch(DuplicateEntityException e){
            return new ResponseEntity<>("La lliga ja existeix", HttpStatus.CONFLICT);
        }catch(Exception error){
            return new ResponseEntity<>("Algun dels camps no és correcte",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary = "Get all the leagues", description = "Get all the leagues given a user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Leagues found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LeagueResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Leagues not found", content = @Content)
    })
    @GetMapping("/league")
    public ResponseEntity<?> getLeagues(@RequestParam("owner")  Integer owner) {
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
            classificationservice.postParticipation(c);
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
            List<ParticipationResponseDTO> classificacio = participationMapper.toResponseDTO(classificationservice.getParticipations(leagueId));
            return new ResponseEntity<>(classificacio,HttpStatus.ACCEPTED);
        }catch(Exception error){
            return new ResponseEntity<>(error.toString(), HttpStatus.NOT_FOUND);
        }

    }

    @Operation(summary = "Get a league by ID", description = "Devuelve una liga por su ID")
    @GetMapping("/league/{leagueId}")
    public ResponseEntity<?> findLeagueById(@PathVariable Integer leagueId) {
        try {
            League league = leagueservice.getLeagueById(leagueId);
            LeagueResponseDTO response = mapper.toResponseDTO(league);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Liga no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a league", description = "Elimina una liga de la base de datos")
    @DeleteMapping("/league/{leagueId}")
    public ResponseEntity<?> deleteLeagueById(@PathVariable int leagueId) {
        try {
            LeagueResponseDTO retorn =  mapper.toResponseDTO(leagueservice.deleteLeagueById(leagueId));
            return ResponseEntity.ok(retorn);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la liga");
        }
    }

    @Operation(summary = "Get all matches from a league", description = "Devuelve todos los enfrentamientos de una liga")
    @GetMapping("/league/{leagueId}/match")
    public ResponseEntity<?> getMatchesByLeagueId(@PathVariable int leagueId) {
        try {
            List<MatchDTO> matchDTOS = matchService.getMatchesByLeagueId(leagueId)
                    .stream()
                    .map(matchmapper::toDTO)
                    .toList();
            return ResponseEntity.ok(matchDTOS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los partidos");
        }
    }

}
