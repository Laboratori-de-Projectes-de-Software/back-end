package org.example.backend.databaseapi.application.port.out.partida;

import org.example.backend.databaseapi.domain.Liga;
import org.example.backend.databaseapi.domain.Partida;

import java.util.Date;

public interface UpdatePartidaPort {

    Partida updatePartida(Liga liga, Integer duraciontotal, Date fecha);

}
