package com.adondeband.back_end_adonde_band.dominio.enfrentamiento;

import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.conversacion.Conversacion;
import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
import com.adondeband.back_end_adonde_band.dominio.jornada.Jornada;
import com.adondeband.back_end_adonde_band.dominio.jornada.JornadaId;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.resultado.RESULTADO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enfrentamiento {

    private EnfrentamientoId id;

    private ESTADO estado;

    private RESULTADO resultado;

    private BotId local;

    private BotId visitante;
    
    private Conversacion conversacion;

    private Integer ronda;

    private LigaId ligaId;

    public Enfrentamiento(EnfrentamientoId id, BotId local, BotId visitante, Conversacion conversacion) {
        this.id = id;
        this.local = local;
        this.visitante = visitante;
        this.conversacion = conversacion;
    }

    BotId getGanador() {
        return switch (resultado) {
            case EMPATE -> null;
            case VICTORIA -> local;
            case DERROTA -> visitante;
        };
    }
}
