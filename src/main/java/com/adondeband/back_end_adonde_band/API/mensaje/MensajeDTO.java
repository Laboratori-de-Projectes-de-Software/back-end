package com.adondeband.back_end_adonde_band.API.mensaje;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MensajeDTO {

    private String text;
    private String botId;
    private LocalDateTime timestamp;
}
