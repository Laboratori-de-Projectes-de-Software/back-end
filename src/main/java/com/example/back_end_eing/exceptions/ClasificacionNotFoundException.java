package com.example.back_end_eing.exceptions;

public class ClasificacionNotFoundException extends RuntimeException {
    public ClasificacionNotFoundException(Long bot, Long liga) {
        super("Clasificacion con bot_id " + bot + " y liga_id " + liga + " no existe");
    }
}
