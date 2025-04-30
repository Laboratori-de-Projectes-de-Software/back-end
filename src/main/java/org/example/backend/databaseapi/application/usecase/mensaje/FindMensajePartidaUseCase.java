package org.example.backend.databaseapi.application.usecase.mensaje;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.mensaje.BuscarMensajesPartidaPort;
import org.example.backend.databaseapi.application.port.out.mensaje.FindMensajePartidaPort;
import org.example.backend.databaseapi.domain.mensaje.Mensaje;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindMensajePartidaUseCase implements BuscarMensajesPartidaPort {

    private final FindMensajePartidaPort findMensajePartidaPort;

    @Override
    public List<Mensaje> buscarMensajesPartida(Integer idPartida) {
        return findMensajePartidaPort.findMensajePartida(idPartida);
    }
}
