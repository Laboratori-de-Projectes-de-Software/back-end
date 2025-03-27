package org.example.backend.databaseapi.domain.partida;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.databaseapi.domain.liga.Liga;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Partida {

    private PartidaId partidaId;
    private Liga liga; //Notnull
    private Estado estado;
    private Integer duracionTotal;
    private LocalDateTime fecha; //Notnull

    @Override
    public String toString() {
        return "Partida{" +
                "partidaId=" + partidaId +
                ", liga=" + liga +
                ", duracionTotal=" + duracionTotal +
                ", fecha=" + fecha +
                '}';
    }
}
