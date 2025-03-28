package org.example.backend.databaseapi.application.controller.partida;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.partida.AltaPartidaPort;
import org.example.backend.databaseapi.application.port.in.partida.BuscarPartidaPort;
import org.example.backend.databaseapi.application.port.in.partida.BuscarPartidasLigaPort;
import org.example.backend.databaseapi.domain.Liga;
import org.example.backend.databaseapi.domain.Partida;
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
    private final PartidaModelAssembler partidaModelAssembler;

    @PostMapping("/ligas/{id}/partidas")
    public ResponseEntity<EntityModel<Partida>> altaPartida(@RequestBody Partida requestPartida, @PathVariable String id){
        Partida partida=altaPartidaPort.altaPartida(requestPartida);
        return ResponseEntity.created(linkTo(methodOn(PartidaController.class).buscarPartida(partida.getLiga().getLigaId(),partida.getPartidaId())).toUri())
                .body(partidaModelAssembler.toModel(partida));
    }

    @GetMapping("/ligas/{idliga}/partidas")
    public ResponseEntity<CollectionModel<EntityModel<Partida>>> buscarPartidasLiga(@PathVariable Integer idliga){
        List<EntityModel<Partida>> ligas= buscarPartidasLigaPort.buscarLigaPartida(idliga)
                .stream()
                .map(partidaModelAssembler::toModel)
                .toList();
        return ResponseEntity.ok(partidaModelAssembler.toCollectionModel(ligas));
    }

    @GetMapping("/ligas/{id}/partidas/{idpartida}")
    public ResponseEntity<EntityModel<Partida>> buscarPartida(@PathVariable Integer id,@PathVariable Integer idpartida){
        Partida partida=buscarPartidaPort.buscarPartida(idpartida);
        return ResponseEntity.created(linkTo(methodOn(PartidaController.class).buscarPartida(partida.getLiga().getLigaId(),partida.getPartidaId())).toUri())
                .body(partidaModelAssembler.toModel(partida));
    }
}
