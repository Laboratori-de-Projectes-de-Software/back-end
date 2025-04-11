package com.adondeband.back_end_adonde_band.API.mensaje;

import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
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

    private String mensaje;
    private BotId idBot;
    private LocalDateTime timestamp;
}
