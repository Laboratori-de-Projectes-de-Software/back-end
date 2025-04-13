package com.adondeband.back_end_adonde_band.dominio.participacion;

import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Participacion {

    private ParticipacionId id;

    private BotId bot;

    private LigaId liga;

    private int posicion;
    private int puntuacion;

    public Participacion(String botId, Long ligaId) {
        this.bot = new BotId(botId);
        this.liga = new LigaId(ligaId);

    }
}
