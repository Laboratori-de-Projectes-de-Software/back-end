package org.example.backend.databaseapi.application.usecase.usuario;

import lombok.RequiredArgsConstructor;
import org.example.backend.databaseapi.application.port.in.usuario.EliminarUsuarioPort;
import org.example.backend.databaseapi.application.port.out.usuario.DeleteUsuarioPort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteUsuarioUseCase implements EliminarUsuarioPort {

    private final DeleteUsuarioPort deleteUsuarioPort;

    public void execute(int id){
        deleteUsuarioPort.deleteUsuario(id);
    }

    @Override
    public void deleteUsuario(Integer userId) {
        deleteUsuarioPort.deleteUsuario(userId);
    }
}
