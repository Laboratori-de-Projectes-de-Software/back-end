package org.example.backend.databaseapi.domain.resultado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.partida.Partida;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Resultado {

    private ResultadoId resultadoId;
    private Integer puntuacion;//notnull

    @Override
    public String toString() {
        return "Resultado{" +
                "botid=" + resultadoId.botvalue() +
                ", partidaid=" + resultadoId.partidavalue() +
                ", puntuacion=" + puntuacion +
                '}';
    }
}
