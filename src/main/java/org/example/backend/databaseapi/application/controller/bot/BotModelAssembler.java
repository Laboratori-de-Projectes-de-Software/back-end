package org.example.backend.databaseapi.application.controller.bot;

import org.example.backend.databaseapi.domain.bot.Bot;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BotModelAssembler implements RepresentationModelAssembler<Bot, EntityModel<Bot>> {
    @Override
    public EntityModel<Bot> toModel(Bot entity) {

        return EntityModel.of(entity,
                linkTo(methodOn(BotController.class).buscarBot(entity.getIdBot().value())).withSelfRel(),
        linkTo(methodOn(BotController.class).buscarAllBots()).withRel("bots"));
    }

    public CollectionModel<EntityModel<Bot>> toCollectionModel(List<EntityModel<Bot>> bots) {

        return CollectionModel.of(bots,
                linkTo(methodOn(BotController.class).buscarAllBots()).withSelfRel());
    }
}
