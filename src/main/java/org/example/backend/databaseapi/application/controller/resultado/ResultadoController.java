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

    //ESTA ES UNA POSIBLE FUNCION QUE PODRIA SERVIR EN UN FUTURO, ACTUALMENTE NO TIENE ENDPOINT ASIGNADO
    public ResponseEntity<Resultado> crearResultado(@RequestBody Resultado resultadoRequest, @PathVariable Integer idpartida){
        Resultado resultado=createResultadoPort.createResultado(resultadoRequest);
        return ResponseEntity.created(linkTo(methodOn(ResultadoController.class)
                        .buscarResultadosPartida(idpartida))
                        .toUri())
                .body(resultado);
    }

    //ESTA ES UNA POSIBLE FUNCION QUE PODRIA SERVIR EN UN FUTURO, ACTUALMENTE NO TIENE ENDPOINT ASIGNADO
    public ResponseEntity<List<Resultado>> buscarResultadosBot(@PathVariable Integer botId){
        List<Resultado> resultados= buscarResultadosBotPort.buscarResultadosBot(botId);
        return ResponseEntity.ok(resultados);
    }

    //ESTA ES UNA POSIBLE FUNCION QUE PODRIA SERVIR EN UN FUTURO, ACTUALMENTE NO TIENE ENDPOINT ASIGNADO
    public ResponseEntity<List<Resultado>> buscarResultadosPartida(@PathVariable Integer partidaId){
        List<Resultado> resultados= buscarResultadosPartidaPort.buscarResultadosPartida(partidaId);
        return ResponseEntity.ok(resultados);
    }

    //ESTA ES UNA POSIBLE FUNCION QUE PODRIA SERVIR EN UN FUTURO, ACTUALMENTE NO TIENE ENDPOINT ASIGNADO
    public ResponseEntity<List<Resultado>> buscarResultadosLiga(@PathVariable Integer idliga){
        List<Resultado> resultados= buscarResultadosLiga.buscarResultadosLiga(idliga);
        return ResponseEntity.ok(resultados);
    }

}
