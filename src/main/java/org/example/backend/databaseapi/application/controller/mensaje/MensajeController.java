package org.example.backend.databaseapi.application.controller.mensaje;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.dto.mensaje.MensajeDTOMapper;
import org.example.backend.databaseapi.application.dto.mensaje.MessageDTOResponse;
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
    private final MensajeDTOMapper mensajeDTOMapper;

    @GetMapping("match/{matchId}/message")
    public ResponseEntity<List<MessageDTOResponse>> buscarMensajesPartida(@PathVariable Integer matchId){
        List<MessageDTOResponse> mensajes=buscarMensajesPartidaPort.buscarMensajesPartida(matchId)
                .stream()
                .map(mensajeDTOMapper::toMessageDTOResponse)
                .toList();
        return ResponseEntity.ok(mensajes);
    }

    @PostMapping("/ligas/{id}/partidas/{idPartida}/mensajes")
    public ResponseEntity<Mensaje> nuevoMensaje(@PathVariable Integer id, @PathVariable Integer idPartida,
                                                             @RequestBody Mensaje requestMensaje){
        Mensaje mensaje=nuevoMensajePort.altaMensaje(requestMensaje);
        return ResponseEntity.created(linkTo(methodOn(MensajeController.class)
                        .buscarMensajesPartida(mensaje.getPartida().value(), mensaje.getMensajeId().value())).toUri())
                .body(mensaje);
    }

}
