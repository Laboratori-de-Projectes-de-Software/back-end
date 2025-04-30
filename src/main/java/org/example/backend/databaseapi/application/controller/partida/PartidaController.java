package org.example.backend.databaseapi.application.controller.partida;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.dto.partida.MatchDTOResponse;
import org.example.backend.databaseapi.application.port.in.partida.AltaPartidaPort;
import org.example.backend.databaseapi.application.port.in.partida.BuscarPartidaPort;
import org.example.backend.databaseapi.application.port.in.partida.BuscarPartidasLigaPort;
import org.example.backend.databaseapi.domain.partida.Partida;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class PartidaController {

    private final AltaPartidaPort altaPartidaPort;
    private final BuscarPartidasLigaPort buscarPartidasLigaPort;
    private final BuscarPartidaPort buscarPartidaPort;


    @GetMapping("/league/{leagueId}/match")
    public ResponseEntity<List<MatchDTOResponse>> buscarPartidasLiga(@PathVariable Integer leagueId){
        List<MatchDTOResponse> ligas= buscarPartidasLigaPort.buscarLigaPartida(leagueId);
        return ResponseEntity.ok(ligas);
    }

    //ESTA ES UNA POSIBLE FUNCION QUE PODRIA SERVIR EN UN FUTURO, ACTUALMENTE NO TIENE ENDPOINT ASIGNADO
    public ResponseEntity<Partida> buscarPartida(@PathVariable Integer idpartida){
        Partida partida=buscarPartidaPort.buscarPartida(idpartida);
        return ResponseEntity.created(linkTo(methodOn(PartidaController.class).buscarPartida(partida.getPartidaId().value())).toUri())
                .body(partida);
    }

    //ESTA ES UNA POSIBLE FUNCION QUE PODRIA SERVIR EN UN FUTURO, ACTUALMENTE NO TIENE ENDPOINT ASIGNADO
    public ResponseEntity<Partida> altaPartida(@RequestBody Partida requestPartida){
        Partida partida=altaPartidaPort.altaPartida(requestPartida);
        return ResponseEntity.created(linkTo(methodOn(PartidaController.class).buscarPartida(partida.getPartidaId().value())).toUri())
                .body(partida);
    }
}
