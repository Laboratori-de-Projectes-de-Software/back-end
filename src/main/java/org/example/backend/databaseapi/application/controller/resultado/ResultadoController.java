package org.example.backend.databaseapi.application.controller.resultado;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.resultado.BuscarResultadosBotPort;
import org.example.backend.databaseapi.application.port.in.resultado.BuscarResultadosLiga;
import org.example.backend.databaseapi.application.port.in.resultado.BuscarResultadosPartidaPort;
import org.example.backend.databaseapi.application.port.out.resultado.CreateResultadoPort;
import org.example.backend.databaseapi.domain.resultado.Resultado;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class ResultadoController {

    private final CreateResultadoPort createResultadoPort;
    private final BuscarResultadosPartidaPort buscarResultadosPartidaPort;
    private final BuscarResultadosBotPort buscarResultadosBotPort;
    private final BuscarResultadosLiga buscarResultadosLiga;
    private final ResultadoModelAssembler resultadoModelAssembler;

    @PostMapping("/ligas/{idliga}/partidas/{idpartida}/resultado")
    public ResponseEntity<EntityModel<Resultado>> crearResultado(@RequestBody Resultado resultadoRequest, @PathVariable Integer idliga, @PathVariable Integer idpartida){
        Resultado resultado=createResultadoPort.createResultado(resultadoRequest);
        return ResponseEntity.created(linkTo(methodOn(ResultadoController.class)
                        .buscarResultadosPartida(
                                resultado.getPartida().getPartidaId().value(),
                                resultado.getPartida().getLiga().getLigaId().value()))
                        .toUri())
                .body(resultadoModelAssembler.toModel(resultado));
    }

    @GetMapping("/ligas/{idliga}/partidas/{idpartida}/resultado/{botId}")
    public ResponseEntity<CollectionModel<EntityModel<Resultado>>> buscarResultadosBot(@PathVariable Integer botId, @PathVariable Integer idliga, @PathVariable Integer idpartida){
        List<EntityModel<Resultado>> resultados= buscarResultadosBotPort.buscarResultadosBot(botId)
                .stream()
                .map(resultadoModelAssembler::toModel)
                .toList();
        return ResponseEntity.ok(resultadoModelAssembler.toCollectionModel(resultados));
    }

    @GetMapping("/ligas/{idliga}/partidas/{partidaId}/resultado")
    public ResponseEntity<CollectionModel<EntityModel<Resultado>>> buscarResultadosPartida(@PathVariable Integer partidaId, @PathVariable Integer idliga){
        List<EntityModel<Resultado>> resultados= buscarResultadosPartidaPort.buscarResultadosPartida(partidaId)
                .stream()
                .map(resultadoModelAssembler::toModel)
                .toList();
        return ResponseEntity.ok(resultadoModelAssembler.toCollectionModel(resultados));
    }

    @GetMapping("/ligas/{idliga}/partidas/resultados")
    public ResponseEntity<CollectionModel<EntityModel<Resultado>>> buscarResultadosLiga(@PathVariable Integer idliga){
        List<EntityModel<Resultado>> resultados= buscarResultadosLiga.buscarResultadosLiga(idliga)
                .stream()
                .map(resultadoModelAssembler::toModel)
                .toList();
        return ResponseEntity.ok(resultadoModelAssembler.toCollectionModel(resultados));
    }

}
