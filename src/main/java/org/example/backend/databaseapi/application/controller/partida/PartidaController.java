package org.example.backend.databaseapi.application.controller.partida;

import lombok.AllArgsConstructor;
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



    @PostMapping("/ligas/{id}/partidas")
    public ResponseEntity<Partida> altaPartida(@RequestBody Partida requestPartida, @PathVariable String id){
        Partida partida=altaPartidaPort.altaPartida(requestPartida);
        return ResponseEntity.created(linkTo(methodOn(PartidaController.class).buscarPartida(partida.getLiga().value(), partida.getPartidaId().value())).toUri())
                .body(partida);
    }

    @GetMapping("/league/{leagueId}/match")
    public ResponseEntity<List<Partida>> buscarPartidasLiga(@PathVariable Integer leagueId){
        List<Partida> ligas= buscarPartidasLigaPort.buscarLigaPartida(leagueId)
                .stream()
                .toList();
        return ResponseEntity.ok(ligas);
    }

    @GetMapping("/ligas/{id}/partidas/{idpartida}")
    public ResponseEntity<Partida> buscarPartida(@PathVariable Integer id,@PathVariable Integer idpartida){
        Partida partida=buscarPartidaPort.buscarPartida(idpartida);
        return ResponseEntity.created(linkTo(methodOn(PartidaController.class).buscarPartida(partida.getLiga().value(), partida.getPartidaId().value())).toUri())
                .body(partida);
    }
}
