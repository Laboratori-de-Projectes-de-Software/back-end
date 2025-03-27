package org.example.backend.databaseapi.application.usecase.usuario;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.usuario.ActualizarUsuarioPort;
import org.example.backend.databaseapi.application.port.out.usuario.UpdateUsuarioPort;
import org.example.backend.databaseapi.domain.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UpdateUsuarioUseCase implements ActualizarUsuarioPort {

    private final UpdateUsuarioPort updateUsuarioPort;

    @Override
    public Usuario actualizarUsuario(Usuario user,Integer id) {
        return updateUsuarioPort.updateUsuario(user,id);
    }
}
