package com.adondeband.back_end_adonde_band.dominio.modelos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enfrentamiento {

    private long id;

    private ESTADO estado;
    private RESULTADO resultado;

    private Bot bot1;

    private Bot bot2;

    private Conversacion conversacion;

    private Jornada jornada;

    Bot getGanador() {
        return switch (resultado) {
            case EMPATE -> null;
            case VICTORIA -> bot1;
            case DERROTA -> bot2;
        };
    }
}
