package org.example.backend.databaseapi.application.usecase.mensaje;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.mensaje.BuscarMensajesBotPort;
import org.example.backend.databaseapi.application.port.out.mensaje.FindMensajeBotPort;
import org.example.backend.databaseapi.domain.mensaje.Mensaje;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindMensajeBotUseCase implements BuscarMensajesBotPort {

    private final FindMensajeBotPort findMensajeBotPort;

    @Override
    public List<Mensaje> buscarMensajesBot(Integer botId) {
        return findMensajeBotPort.findMensajeBot(botId);
    }
}
