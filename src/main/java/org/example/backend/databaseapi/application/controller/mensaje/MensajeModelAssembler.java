package org.example.backend.databaseapi.application.controller.mensaje;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.controller.liga.LigaController;
import org.example.backend.databaseapi.domain.mensaje.Mensaje;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class MensajeModelAssembler implements RepresentationModelAssembler<Mensaje, EntityModel<Mensaje>> {
    @Override
    @NonNull
    public EntityModel<Mensaje> toModel(@NonNull Mensaje entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(MensajeController.class).buscarMensajesPartida(entity.getPartida().value(), entity.getMensajeId().value())).withSelfRel());
    }

    public CollectionModel<EntityModel<Mensaje>> toCollectionModel(List<EntityModel<Mensaje>> mensajes) {
        return CollectionModel.of(mensajes,
                linkTo(methodOn(LigaController.class).buscarAllLigas()).withSelfRel());
    }
}
