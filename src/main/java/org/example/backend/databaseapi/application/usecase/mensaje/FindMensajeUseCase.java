package org.example.backend.databaseapi.application.usecase.mensaje;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.mensaje.BuscarMensajePort;
import org.example.backend.databaseapi.application.port.out.mensaje.FindMensajePort;
import org.example.backend.databaseapi.domain.mensaje.Mensaje;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindMensajeUseCase implements BuscarMensajePort {

    private final FindMensajePort findMensajePort;

    @Override
    public Mensaje buscarMensaje(Integer idMensaje) {
        return findMensajePort.findMensaje(idMensaje)
                .orElseThrow(()->new RuntimeException("Error el mensaje no existe"));
    }

}
