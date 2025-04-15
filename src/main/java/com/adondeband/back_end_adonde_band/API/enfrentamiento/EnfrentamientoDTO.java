package com.adondeband.back_end_adonde_band.API.enfrentamiento;

import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
import com.adondeband.back_end_adonde_band.dominio.resultado.RESULTADO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnfrentamientoDTO {
    private Long id;
    private ESTADO state;
    private RESULTADO result;
    private Long [] fighters;
    private int roundNumber;

    Long getGanador() {
        return switch (result) {
            case EMPATE   -> null;
            case VICTORIA -> fighters[0];
            case DERROTA  -> fighters[1];
        };
    }
}
