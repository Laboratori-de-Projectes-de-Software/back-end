package com.example.back_end_eing.exceptions;

public class ClasificacionLigaNotFoundException extends RuntimeException {
    public ClasificacionLigaNotFoundException(Long liga) {
        super("Clasificacion con liga_id " + liga + " no existe");
    }
}
