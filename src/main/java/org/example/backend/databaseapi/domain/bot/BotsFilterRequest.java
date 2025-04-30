package org.example.backend.databaseapi.domain.bot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Builder
@Getter
@Setter
public class BotsFilterRequest {

    private String cualidad;
    private String usuario;
    private String nombre;
    private int orden;  // 0 sin ordenar, 1 partidas ganadas, 2 partidas perdidas, 3 alfabéticamente, 4 alfabéticamente inverso

}
