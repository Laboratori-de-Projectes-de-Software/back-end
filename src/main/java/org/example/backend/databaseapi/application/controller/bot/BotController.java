package org.example.backend.databaseapi.application.controller.bot;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.dto.bot.BotDTOMapper;
import org.example.backend.databaseapi.application.dto.bot.BotDTORequest;
import org.example.backend.databaseapi.application.dto.bot.BotDTOResponse;
import org.example.backend.databaseapi.application.port.in.bot.*;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.bot.BotsFilterRequest;
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

    @PostMapping("/bot")
    ResponseEntity<BotDTOResponse> altaBot(@RequestBody BotDTORequest newBot){
        Bot bot=altaBotPort.altaBot(botDTOMapper.toBot(newBot));
        BotDTOResponse response=botDTOMapper.toDTOResponse(bot);
        response.setNWins(0);
        response.setNLoses(0);
        response.setNDraws(0);
        return ResponseEntity.created(linkTo(methodOn(BotController.class).buscarBot(bot.getIdBot().value())).toUri())
                .body(response);
    }

    @DeleteMapping("/bots/{id}")
    ResponseEntity<?> eliminarBot(@PathVariable Integer id){
        eliminarBotPort.eliminarBot(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/bot/{id}")
    ResponseEntity<BotDTOResponse> buscarBot(@PathVariable Integer id){
        Bot bot=buscarBotPort.buscarBot(id);
        return ResponseEntity.ok(botDTOMapper.toDTOResponse(bot));
    }

    @GetMapping("/bot")
    ResponseEntity<List<BotDTOResponse>> buscarAllBots(@RequestParam("owner") Optional<Integer> userId ){
        List<BotDTOResponse> bots = userId.map(
                        integer -> buscarAllUserBotsPort.buscarUserBots(integer)
                        .stream()
                        .map(botDTOMapper::toDTOResponse)
                        .toList()
                )
                .orElseGet(
                        () -> buscarAllBotsPort.buscarAllBots()
                        .stream()
                        .map(botDTOMapper::toDTOResponse)
                        .toList()
                );

        return ResponseEntity.ok(bots);
    }

    @PatchMapping("/bots")
    ResponseEntity<List<Bot>> buscarAllBotsFilter(@RequestBody BotsFilterRequest request){
        List<Bot> bots = buscarAllBotsPort.buscarAllBotsFiltro(request)
                .stream()
                .toList();
        return ResponseEntity.ok(bots);
    }

    @GetMapping("/usuario/{id}/bots")
    ResponseEntity<List<Bot>> buscarUserBots(@PathVariable Integer id){
        List<Bot> bots=buscarAllUserBotsPort.buscarUserBots(id)
                .stream()
                .toList();
        return ResponseEntity.ok(bots);
    }

    @PutMapping("bot/{id}")
    ResponseEntity<BotDTOResponse> actualizarBot(@RequestBody BotDTORequest changedBot,@PathVariable Integer id){
        Bot bot=actualizarBotPort.actualizarBot(botDTOMapper.toBot(changedBot), id);
        return ResponseEntity.created(linkTo(methodOn(BotController.class).buscarBot(bot.getIdBot().value())).toUri())
                .body(botDTOMapper.toDTOResponse(bot));
    }

}
