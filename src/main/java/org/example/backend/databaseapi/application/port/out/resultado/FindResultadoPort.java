package org.example.backend.databaseapi.application.port.out.resultado;

import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.partida.Partida;
import org.example.backend.databaseapi.domain.resultado.Resultado;

import java.util.Optional;

public interface FindResultadoPort {

    Optional<Resultado> findResultado(Bot bot, Partida partida);

}
