package org.example.backend.databaseapi.jpa.resultado;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.databaseapi.jpa.bot.BotJpaEntity;
import org.example.backend.databaseapi.jpa.partida.PartidaJpaEntity;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Getter
public class ResultadoId implements Serializable {

    private BotJpaEntity bot;

    private PartidaJpaEntity partida;

}
