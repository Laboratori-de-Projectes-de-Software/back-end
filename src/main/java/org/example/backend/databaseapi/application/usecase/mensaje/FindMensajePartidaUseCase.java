package org.example.backend.databaseapi.application.usecase.mensaje;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.mensaje.BuscarMensajesPartidaPort;
import org.example.backend.databaseapi.application.port.out.mensaje.FindMensajePartidaPort;
import org.example.backend.databaseapi.domain.Mensaje;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FindMensajePartidaUseCase implements BuscarMensajesPartidaPort {

    private final FindMensajePartidaPort findMensajePartidaPort;

    @Override
    public List<Mensaje> buscarMensajesPartida(Integer idPartida) {
        return findMensajePartidaPort.findMensajePartida(idPartida);
    }
}
