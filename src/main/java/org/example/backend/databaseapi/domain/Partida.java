package org.example.backend.databaseapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Partida {

    private Integer partidaId;
    private Liga liga; //Notnull
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
