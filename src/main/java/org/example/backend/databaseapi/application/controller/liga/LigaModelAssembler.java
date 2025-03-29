package org.example.backend.databaseapi.application.controller.liga;

import org.example.backend.databaseapi.domain.liga.Liga;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LigaModelAssembler implements RepresentationModelAssembler<Liga, EntityModel<Liga>> {

    @Override
    @NonNull
    public EntityModel<Liga> toModel(@NonNull Liga entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(LigaController.class).buscarLiga(entity.getLigaId().value())).withSelfRel(),
                linkTo(methodOn(LigaController.class).buscarAllLigas()).withRel("ligas"));
    }

    public CollectionModel<EntityModel<Liga>> toCollectionModel(List<EntityModel<Liga>> ligas) {

        return CollectionModel.of(ligas,
                linkTo(methodOn(LigaController.class).buscarAllLigas()).withSelfRel());
    }
}
