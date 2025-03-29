package org.example.backend.databaseapi.application.controller.usuario;

import org.example.backend.databaseapi.domain.usuario.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {
    @Override
    @NonNull
    public EntityModel<Usuario> toModel(@NonNull Usuario entity) {
        //linkTo(methodOn(OrderController.class).all()).withRel("orders"));

        return EntityModel.of(entity,
                linkTo(methodOn(UsuarioController.class).buscarUsuario(entity.getUserId().value())).withSelfRel());
    }
}
