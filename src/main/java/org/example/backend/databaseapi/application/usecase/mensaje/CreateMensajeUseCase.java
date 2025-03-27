package org.example.backend.databaseapi.application.usecase.mensaje;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.mensaje.NuevoMensajePort;
import org.example.backend.databaseapi.application.port.out.mensaje.CreateMensajePort;
import org.example.backend.databaseapi.domain.mensaje.Mensaje;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateMensajeUseCase implements NuevoMensajePort {

    private final CreateMensajePort createMensajePort;

    @Override
    public Mensaje altaMensaje(Mensaje mensaje) {
        return createMensajePort.createMensaje(mensaje);
    }
}
