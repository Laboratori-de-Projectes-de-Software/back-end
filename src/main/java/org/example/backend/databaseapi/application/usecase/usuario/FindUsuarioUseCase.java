package org.example.backend.databaseapi.application.usecase.usuario;

import lombok.RequiredArgsConstructor;
import org.example.backend.databaseapi.application.exception.ResourceNotFoundException;
import org.example.backend.databaseapi.application.port.in.usuario.BuscarUsuarioPort;
import org.example.backend.databaseapi.application.port.out.usuario.FindUsuarioPort;
import org.example.backend.databaseapi.domain.usuario.Usuario;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUsuarioUseCase implements BuscarUsuarioPort {

    private final FindUsuarioPort findUserPort;

    @Override
    public Usuario buscarUsuario(Integer userId) {
        return findUserPort.findUsuario(userId)
                .orElseThrow(()->new ResourceNotFoundException("El usuario no existe"));
    }

    @Override
    public Usuario buscarUsuario(String email) {
        return findUserPort.findUsuario(email)
                .orElseThrow(()->new ResourceNotFoundException("El usuario no existe"));
    }
}
