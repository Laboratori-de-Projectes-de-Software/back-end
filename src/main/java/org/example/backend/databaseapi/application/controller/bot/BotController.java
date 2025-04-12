package org.example.backend.databaseapi.application.controller.bot;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.example.backend.databaseapi.application.dto.bot.BotDTOMapper;
import org.example.backend.databaseapi.application.dto.bot.BotDTORequest;
import org.example.backend.databaseapi.application.dto.bot.BotDTOResponse;
import org.example.backend.databaseapi.application.port.in.bot.*;
import org.example.backend.databaseapi.application.service.JwtService;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.bot.BotsFilterRequest;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class BotController {

    private final AltaBotPort altaBotPort;
    private final BuscarAllBotsPort buscarAllBotsPort;
    private final BuscarAllUserBotsPort buscarAllUserBotsPort;
    private final BuscarBotPort buscarBotPort;
    private final EliminarBotPort eliminarBotPort;
    private final ActualizarBotPort actualizarBotPort;
    private final BotDTOMapper botDTOMapper;
    private final JwtService jwtService;

    @PostMapping("/bot")
    public ResponseEntity<BotDTOResponse> altaBot(@RequestBody BotDTORequest newBot,@RequestHeader("Authorization") String jwtToken){
        String jwt = jwtToken.substring(7);
        Bot bot=botDTOMapper.toBot(newBot);
        bot.setUsuario(new UsuarioId(jwtService.extractUserId(jwt)));
        bot=altaBotPort.altaBot(bot);

        BotDTOResponse response=botDTOMapper.toDTOResponse(bot);
        //Al crear un bot no tiene derrotas, victorias ni empates por lo que se devuelve 0
        response.setNWins(0);
        response.setNLosses(0);
        response.setNDraws(0);
        return ResponseEntity.created(linkTo(methodOn(BotController.class).buscarBot(bot.getIdBot().value())).toUri())
                .body(response);
    }

    @GetMapping("/bot")
    public ResponseEntity<List<BotDTOResponse>> buscarAllBots(@RequestParam("owner") Optional<Integer> userId ){
        //Si el query parameter userId no esta vacio entonces se buscan los bots
        //de ese usuario,si no, se buscan todos los bots
        List<BotDTOResponse> bots = userId.map(buscarAllUserBotsPort::buscarUserBots)
                .orElseGet(buscarAllBotsPort::buscarAllBots);

        return ResponseEntity.ok(bots);
    }

    @GetMapping("/bot/{botId}")
    public ResponseEntity<BotDTOResponse> buscarBot(@PathVariable Integer botId){
        BotDTOResponse bot=buscarBotPort.buscarBot(botId);
        return ResponseEntity.ok(bot);
    }


    @PutMapping("/bot/{botId}")
    public ResponseEntity<BotDTOResponse> actualizarBot(@RequestBody BotDTORequest changedBot,@PathVariable Integer botId
            ,@RequestHeader("Authorization") String jwtToken){
        String jwt = jwtToken.substring(7);
        Bot botrequested=botDTOMapper.toBot(changedBot);
        botrequested.setUsuario(new UsuarioId(jwtService.extractUserId(jwt)));
        BotDTOResponse bot=actualizarBotPort.actualizarBot(botrequested, botId);
        return ResponseEntity.created(linkTo(methodOn(BotController.class).buscarBot(bot.getBotId())).toUri())
                .body(bot);
    }

    //ESTA ES UNA POSIBLE FUNCION QUE PODRIA SERVIR EN UN FUTURO, ACTUALMENTE NO TIENE ENDPOINT ASIGNADO
    public ResponseEntity<?> eliminarBot(@PathVariable Integer id){
        eliminarBotPort.eliminarBot(id);
        return ResponseEntity.noContent().build();
    }

    //ESTA ES UNA POSIBLE FUNCION QUE PODRIA SERVIR EN UN FUTURO, ACTUALMENTE NO TIENE ENDPOINT ASIGNADO
    public ResponseEntity<List<Bot>> buscarAllBotsFilter(@RequestBody BotsFilterRequest request){
        List<Bot> bots = buscarAllBotsPort.buscarAllBotsFiltro(request)
                .stream()
                .toList();
        return ResponseEntity.ok(bots);
    }

    //ESTA ES UNA POSIBLE FUNCION QUE PODRIA SERVIR EN UN FUTURO, ACTUALMENTE NO TIENE ENDPOINT ASIGNADO
    public ResponseEntity<List<BotDTOResponse>> buscarUserBots(@PathVariable Integer id){
        List<BotDTOResponse> bots=buscarAllUserBotsPort.buscarUserBots(id)
                .stream()
                .toList();
        return ResponseEntity.ok(bots);
    }



}
