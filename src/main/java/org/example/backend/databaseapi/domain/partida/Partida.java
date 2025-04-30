package org.example.backend.databaseapi.domain.partida;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.databaseapi.application.dto.partida.Result;
import org.example.backend.databaseapi.domain.liga.LigaId;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Partida {

    private PartidaId partidaId;
    private LigaId liga; //Notnull
    private Result result;
    private Integer roundNumber;
    private Estado estado;
    private Long duracionTotal;

}
