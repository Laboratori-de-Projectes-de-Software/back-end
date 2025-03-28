package org.example.backend.databaseapi.application.controller.resultado;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.controller.liga.LigaController;
import org.example.backend.databaseapi.application.controller.mensaje.MensajeController;
import org.example.backend.databaseapi.domain.Mensaje;
import org.example.backend.databaseapi.domain.Resultado;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class ResultadoModelAssembler implements RepresentationModelAssembler<Resultado, EntityModel<Resultado>> {

    @Override
    public EntityModel<Resultado> toModel(Resultado entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ResultadoController.class).buscarResultadosPartida(entity.getPartida().getPartidaId(),entity.getPartida().getLiga().getLigaId())).withSelfRel());
    }

    public CollectionModel<EntityModel<Resultado>> toCollectionModel(List<EntityModel<Resultado>> resultados) {
        return CollectionModel.of(resultados,
                linkTo(methodOn(ResultadoController.class).buscarResultadosLiga(resultados.getFirst().getContent().getPartida().getPartidaId())).withSelfRel());
    }
}
