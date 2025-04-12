package org.example.backend.databaseapi.application.controller.liga;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.bot.EliminarBotPort;
import org.example.backend.databaseapi.application.port.in.liga.AltaLigaPort;
import org.example.backend.databaseapi.application.port.in.liga.BuscarAllLigasPort;
import org.example.backend.databaseapi.application.port.in.liga.BuscarLigaPort;
import org.example.backend.databaseapi.application.port.in.liga.EliminarLigaPort;
import org.example.backend.databaseapi.application.dto.liga.LeagueDTORequest;
import org.example.backend.databaseapi.application.dto.liga.LeagueDTOResponse;
import org.example.backend.databaseapi.application.dto.liga.LigaDTOMapper;
import org.example.backend.databaseapi.application.dto.resultado.ParticipationDTOResponse;
import org.example.backend.databaseapi.application.port.in.liga.*;
import org.example.backend.databaseapi.application.service.JwtService;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class LigaController {

    private final AltaLigaPort altaLigaPort;
    private final BuscarLigaPort buscarLigaPort;
    private final BuscarAllLigasPort buscarAllLigasPort;
    private final AnadirBotLigaPort anadirBotLigaPort;
    private final BuscarLigaUsuarioPort buscarLigaUsuarioPort;
    private final ActualizarLigaPort actualizarLigaPort;
    private final EliminarLigaPort eliminarLigaPort;
    private final ClasificacionLigaPort clasificacionLigaPort;
    private final IniciarLigaPort iniciarLigaPort;
    private final LigaDTOMapper ligaDTOMapper;
    private final JwtService jwtService;

    @PostMapping("/league")
    public ResponseEntity<LeagueDTOResponse> altaLiga(@RequestBody LeagueDTORequest requestLiga, @RequestHeader("Authorization") String jwtToken){
        String jwt = jwtToken.substring(7);
        Liga liga=ligaDTOMapper.toLiga(requestLiga);
        liga.setUsuario(new UsuarioId(jwtService.extractUserId(jwt)));
        liga=altaLigaPort.altaLiga(liga);
        return ResponseEntity.created(linkTo(methodOn(LigaController.class).buscarLiga(liga.getLigaId().value())).toUri())
                .body(ligaDTOMapper.toLeagueDTOResponse(liga));
    }

    @PostMapping("/league/{leagueId}/bot")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBot(@RequestBody Integer botId,@PathVariable Integer leagueId){
        anadirBotLigaPort.anadirBotLiga(botId,leagueId);
    }

    @PostMapping("/league/{leagueId}/start")
    @ResponseStatus(HttpStatus.CREATED)
    public void startLeague(@PathVariable Integer leagueId){
        iniciarLigaPort.iniciarLiga(leagueId);
    }

    @GetMapping("/league/{leagueId}")
    public ResponseEntity<LeagueDTOResponse> buscarLiga(@PathVariable Integer leagueId){
        Liga liga=buscarLigaPort.buscarLiga(leagueId);
        return ResponseEntity.ok(ligaDTOMapper.toLeagueDTOResponse(liga));
    }

    @GetMapping("/league/{leagueId}/leaderboard")
    public ResponseEntity<List<ParticipationDTOResponse>> buscarClasificacion(@PathVariable Integer leagueId){
        return ResponseEntity.ok(clasificacionLigaPort.getClasificacionesLiga(leagueId));
    }

    @PutMapping("/league/{leagueId}")
    public ResponseEntity<LeagueDTOResponse> actualizarLiga(@PathVariable Integer leagueId, @RequestBody LeagueDTORequest request,
                                                            @RequestHeader("Authorization") String jwtToken){
        Liga liga=ligaDTOMapper.toLiga(request);
        String jwt = jwtToken.substring(7);
        liga.setUsuario(new UsuarioId(jwtService.extractUserId(jwt)));
        liga=actualizarLigaPort.actualizarLiga(liga,leagueId);
        return ResponseEntity.ok(ligaDTOMapper.toLeagueDTOResponse(liga));
    }

    @DeleteMapping("/league/{leagueId}")
    public ResponseEntity<LeagueDTOResponse> eliminarLiga(@PathVariable Integer leagueId, @RequestHeader("Authorization") String jwtToken){
        String jwt = jwtToken.substring(7);
        Liga liga=eliminarLigaPort.eliminarLiga(leagueId,jwtService.extractUserId(jwt));
        return ResponseEntity.ok(ligaDTOMapper.toLeagueDTOResponse(liga));
    }


    @GetMapping("/league")
    public ResponseEntity<List<LeagueDTOResponse>> buscarAllLigas(@RequestParam("owner") Optional<Integer> userId){
        List<LeagueDTOResponse> ligas= userId.map(
                id-> buscarLigaUsuarioPort.buscarLigasUsuario(id)
                        .stream()
                        .map(ligaDTOMapper::toLeagueDTOResponse)
                        .toList()
        ).orElseGet(
                ()->buscarAllLigasPort.buscarAllLigas()
                        .stream()
                        .map(ligaDTOMapper::toLeagueDTOResponse)
                        .toList()
        );
        return ResponseEntity.ok(ligas);
    }
    /*
     comentado para no desperdiciar mi esfuerzo y para no añadir algo que no se ha pedido
     pero quizá sí se pedirá luego (además viene bien para testear)
     */
    /*@DeleteMapping("/ligas/{id}")
    ResponseEntity<?> eliminarLiga(@PathVariable Integer id){
        eliminarLigaPort.eliminarLiga(id);
        return ResponseEntity.noContent().build();
    }*/
}
