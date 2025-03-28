package org.example.backend.databaseapi.application.port.out.mensaje;

import org.example.backend.databaseapi.domain.Mensaje;


import java.util.List;

public interface FindMensajeBotPort {

    List<Mensaje> findMensajeBot(Integer botId);

}
