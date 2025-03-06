package com.adondeband.back_end_adonde_band.API.DTO;

import com.adondeband.back_end_adonde_band.dominio.modelos.*;

public class EnfrentamientoDTO {
    private long id;

    private ESTADO estado;
    private RESULTADO resultado;

    private String bot1;

    private String bot2;

    String getGanador() {
        return switch (resultado) {
            case EMPATE -> null;
            case VICTORIA -> bot1;
            case DERROTA -> bot2;
        };
    }
}
