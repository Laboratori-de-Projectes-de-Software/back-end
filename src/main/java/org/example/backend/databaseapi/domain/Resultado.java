package org.example.backend.databaseapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Resultado {

    private Bot bot;
    private Partida partida;
    private Integer puntuacion;//notnull

    @Override
    public String toString() {
        return "Resultado{" +
                "bot=" + bot +
                ", partida=" + partida +
                ", puntuacion=" + puntuacion +
                '}';
    }
}
