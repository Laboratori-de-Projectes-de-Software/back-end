package org.example.backend.databaseapi.application.controller.liga;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.liga.AltaLigaPort;
import org.example.backend.databaseapi.application.port.in.liga.BuscarAllLigasPort;
import org.example.backend.databaseapi.application.port.in.liga.BuscarLigaPort;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class LigaController {

    private final AltaLigaPort altaLigaPort;
    private final BuscarLigaPort buscarLigaPort;
    private final BuscarAllLigasPort buscarAllLigasPort;
    private final LigaModelAssembler ligaModelAssembler;

    @PostMapping("/ligas")
    public ResponseEntity<EntityModel<Liga>> altaLiga(@RequestBody Liga requestLiga){
        Liga liga=altaLigaPort.altaLiga(requestLiga);
        return ResponseEntity.created(linkTo(methodOn(LigaController.class).buscarLiga(liga.getLigaId().value())).toUri())
                .body(ligaModelAssembler.toModel(liga));
    }

    @GetMapping("/ligas/{id}")
    public ResponseEntity<EntityModel<Liga>> buscarLiga(@PathVariable Integer id){
        Liga liga=buscarLigaPort.buscarLiga(id);
        return ResponseEntity.ok(ligaModelAssembler.toModel(liga));
    }


    @GetMapping("/ligas")
    public ResponseEntity<CollectionModel<EntityModel<Liga>>> buscarAllLigas(){
        List<EntityModel<Liga>> ligas= buscarAllLigasPort.buscarAllLigas()
                .stream()
                .map(ligaModelAssembler::toModel)
                .toList();
        return ResponseEntity.ok(ligaModelAssembler.toCollectionModel(ligas));
    }

}
