package com.adondeband.back_end_adonde_band.dominio.mensajes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mensajes {
    private List<Mensaje> msgsL;
    private List<Mensaje> msgsV;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Mensaje {
        private String mensaje;
        private LocalDateTime timestamp;
    }
}
