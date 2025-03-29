package org.example.backend.databaseapi.domain.mensaje;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.databaseapi.domain.bot.BotId;
import org.example.backend.databaseapi.domain.partida.PartidaId;

import java.sql.Timestamp;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Mensaje {

    private MensajeId mensajeId;
    private BotId bot;
    private PartidaId partida;
    private String texto; //Notnull
    private Timestamp hora; //Notnull

}
