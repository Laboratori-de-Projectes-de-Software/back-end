package org.example.backend.databaseapi.application.usecase.usuario;

import lombok.RequiredArgsConstructor;
import org.example.backend.databaseapi.application.exception.ResourceAlreadyExistsException;
import org.example.backend.databaseapi.application.port.in.usuario.AltaUsuarioPort;
import org.example.backend.databaseapi.application.port.out.usuario.CreateUsuarioPort;
import org.example.backend.databaseapi.domain.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUsuarioUseCase implements AltaUsuarioPort {

    private final CreateUsuarioPort createUsuarioPort;

    @Override
    public Usuario altaUsuario(Usuario user) {
        return createUsuarioPort.createUsuario(user)
                .orElseThrow(()->new ResourceAlreadyExistsException("Ya existe un usuario con ese email"));
    }
}
