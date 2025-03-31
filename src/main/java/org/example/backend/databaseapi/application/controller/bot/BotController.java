package org.example.backend.databaseapi.application.controller.bot;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.bot.*;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.bot.BotsFilterRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private final BotModelAssembler botModelAssembler;

    @PostMapping("/bots")
    ResponseEntity<EntityModel<Bot>> altaBot(@RequestBody Bot newBot){
        Bot bot=altaBotPort.altaBot(newBot);
        return ResponseEntity.created(linkTo(methodOn(BotController.class).buscarBot(bot.getIdBot().value())).toUri())
                .body(botModelAssembler.toModel(bot));
    }

    @DeleteMapping("/bots/{id}")
    ResponseEntity<?> eliminarBot(@PathVariable Integer id){
        eliminarBotPort.eliminarBot(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/bots/{id}")
    ResponseEntity<EntityModel<Bot>> buscarBot(@PathVariable Integer id){
        Bot bot=buscarBotPort.buscarBot(id);
        return ResponseEntity.ok(botModelAssembler.toModel(bot));
    }

    @GetMapping("/bots")
    ResponseEntity<CollectionModel<EntityModel<Bot>>> buscarAllBots(){
        List<EntityModel<Bot>> bots=buscarAllBotsPort.buscarAllBots()
                .stream()
                .map(botModelAssembler::toModel)
                .toList();
        return ResponseEntity.ok(botModelAssembler.toCollectionModel(bots));
    }

    @PatchMapping("/bots")
    ResponseEntity<CollectionModel<EntityModel<Bot>>> buscarAllBotsFilter(@RequestBody BotsFilterRequest request){
        List<EntityModel<Bot>> bots = buscarAllBotsPort.buscarAllBotsFiltro(request)
                .stream()
                .map(botModelAssembler::toModel)
                .toList();
        return ResponseEntity.ok(botModelAssembler.toCollectionModel(bots));
    }

    @GetMapping("/usuario/{id}/bots")
    ResponseEntity<CollectionModel<EntityModel<Bot>>> buscarUserBots(@PathVariable Integer id){
        List<EntityModel<Bot>> bots=buscarAllUserBotsPort.buscarUserBots(id)
                .stream()
                .map(botModelAssembler::toModel)
                .toList();
        return ResponseEntity.ok(botModelAssembler.toCollectionModel(bots));
    }

    @PatchMapping("bots/{id}")
    ResponseEntity<EntityModel<Bot>> actualizarBot(@RequestBody Bot changedBot,@PathVariable Integer id){

        Bot bot=actualizarBotPort.actualizarBot(changedBot, id);
        return ResponseEntity.created(linkTo(methodOn(BotController.class).buscarBot(bot.getIdBot().value())).toUri())
                .body(botModelAssembler.toModel(bot));
    }

}
