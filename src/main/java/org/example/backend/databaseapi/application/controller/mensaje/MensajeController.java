package org.example.backend.databaseapi.application.controller.mensaje;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.mensaje.BuscarMensajePort;
import org.example.backend.databaseapi.application.port.in.mensaje.BuscarMensajesBotPort;
import org.example.backend.databaseapi.application.port.in.mensaje.BuscarMensajesPartidaPort;
import org.example.backend.databaseapi.application.port.in.mensaje.NuevoMensajePort;
import org.example.backend.databaseapi.domain.mensaje.Mensaje;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class MensajeController {

    private final NuevoMensajePort nuevoMensajePort;
    private final BuscarMensajePort buscarMensajePort;
    private final BuscarMensajesPartidaPort buscarMensajesPartidaPort;
    private final BuscarMensajesBotPort buscarMensajesBotPort;
    private final MensajeModelAssembler mensajeModelAssembler;

    @GetMapping("/ligas/{id}/partidas/{idPartida}/mensajes")
    public ResponseEntity<CollectionModel<EntityModel<Mensaje>>> buscarMensajesPartida(@PathVariable Integer id, @PathVariable Integer idPartida){
        List<EntityModel<Mensaje>> mensajes=buscarMensajesPartidaPort.buscarMensajesPartida(idPartida)
                .stream()
                .map(mensajeModelAssembler::toModel)
                .toList();
        return ResponseEntity.ok(mensajeModelAssembler.toCollectionModel(mensajes));
    }

    @PostMapping("/ligas/{id}/partidas/{idPartida}/mensajes")
    public ResponseEntity<EntityModel<Mensaje>> nuevoMensaje(@PathVariable Integer id, @PathVariable Integer idPartida,
                                                             @RequestBody Mensaje requestMensaje){
        Mensaje mensaje=nuevoMensajePort.altaMensaje(requestMensaje);
        return ResponseEntity.created(linkTo(methodOn(MensajeController.class)
                        .buscarMensajesPartida(mensaje.getPartida().getPartidaId().value(), mensaje.getMensajeId().value())).toUri())
                .body(mensajeModelAssembler.toModel(mensaje));
    }

}
