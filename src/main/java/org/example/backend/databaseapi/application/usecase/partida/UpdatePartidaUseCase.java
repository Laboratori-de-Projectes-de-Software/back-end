package org.example.backend.databaseapi.application.usecase.partida;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.out.partida.UpdatePartidaPort;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.example.backend.databaseapi.domain.partida.Partida;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@AllArgsConstructor
public class UpdatePartidaUseCase {

    private final UpdatePartidaPort updatePartidaPort;

    public Partida execute(Liga liga, Integer duraciontotal, Date fecha){
        return updatePartidaPort.updatePartida(liga,duraciontotal,fecha);
    }

}
