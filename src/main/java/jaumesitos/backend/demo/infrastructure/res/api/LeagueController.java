package jaumesitos.backend.demo.infrastructure.res.api;


import jaumesitos.backend.demo.infrastructure.res.dto.LligaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import lombok.RequiredArgsConstructor;

import jaumesitos.backend.demo.infrastructure.res.mapper.LligaDTOMapper;
import jaumesitos.backend.demo.application.service.LligaService;

@RequiredArgsConstructor
@RestController

@RequestMapping("")
@Tag(name = "League Controller", description = "Endpoints for managing leagues")
public class LeagueController {

    private final LligaDTOMapper mapper;
    private final LligaService service;

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

    @Operation(summary = "Home endpoint", description = "Returns a welcome message")
    @GetMapping("")
    public String home() {
        return "Hello, Spring Boot!";
    }

    @Operation(summary = "Post a League", description = "Afegeix una lliga a la base de dades")
    @PostMapping("/leagues")
    public ResponseEntity<?> postLliga(@RequestBody LligaDTO lligadto) {

//        return new ResponseEntity<>(mapper.toDTO(service.postLliga(mapper.toDomain(lligadto))),
//                HttpStatus.CREATED);
        return new ResponseEntity<>("Endpoint POST /api/leagues -> POST A LEAGUE", HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Get All Leagues", description = "Per obtenir totes les lligues")
    @GetMapping("/leagues")
    public ResponseEntity<?> getAllLeagues() {
        return new ResponseEntity<>("Endpoint GET /api/leagues -> GET ALL LEAGUES", HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Get a League by Id", description = "Per obtenir una lliga")
    @GetMapping("/leagues/{id}")
    public ResponseEntity<?> getLeagueById(@PathVariable int id) {
        try {
            var lliga = service.getLeagueById(id);
            if (lliga == null) {
                return new ResponseEntity<>("Liga no encontrada", HttpStatus.NOT_FOUND);
            }
            LligaDTO dto = mapper.toDTO(lliga);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Update a League by Id", description = "Per actualitzar una lliga")
    @PutMapping("/leagues/{id}")
    public ResponseEntity<?> updateLeague(@PathVariable Long id, @RequestBody LligaDTO lligadto) {
        return new ResponseEntity<>("Endpoint PUT /api/leagues/" + id + " -> UPDATE A LEAGUE", HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete a League by Id", description = "Per eliminar una lliga")
    @DeleteMapping("/leagues/{id}")
    public ResponseEntity<?> deleteLeague(@PathVariable Long id) {
        return new ResponseEntity<>("Endpoint DELETE /api/leagues/" + id + " -> DELETE A LEAGUE", HttpStatus.ACCEPTED);
    }

}
