package org.example.backend.databaseapi.application.controller.partida;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.controller.liga.LigaController;
import org.example.backend.databaseapi.domain.partida.Partida;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class PartidaModelAssembler implements RepresentationModelAssembler<Partida, EntityModel<Partida>> {
    @Override
    public EntityModel<Partida> toModel(Partida entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(PartidaController.class).buscarPartida(entity.getLiga().getLigaId().value(), entity.getPartidaId().value())).withSelfRel());
    }

    public CollectionModel<EntityModel<Partida>> toCollectionModel(List<EntityModel<Partida>> partida) {
        return CollectionModel.of(partida,
                linkTo(methodOn(LigaController.class).buscarAllLigas()).withSelfRel());
    }
}
