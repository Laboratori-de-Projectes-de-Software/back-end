package org.example.backend.databaseapi.jpa.resultado;


import lombok.*;
import org.example.backend.databaseapi.jpa.bot.BotJpaEntity;
import org.example.backend.databaseapi.jpa.partida.PartidaJpaEntity;

import java.io.Serializable;



public record ResultadoId(BotJpaEntity bot, PartidaJpaEntity partida) implements Serializable {

}
